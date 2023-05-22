<template>
  <div class="col-md-11 container-fluid mx-auto mb-3">
    <div class="row">
      <div class="col-10 p-0">
        <div class="map_wrap" style="position: relative; width: 100%; height: 700px">
          <div id="map"></div>
          <div id="menu_wrap" class="bg_white">
            <div class="option">
              <div class="d-flex justify-content-between p-2">
                <input class="form-control form-control-sm p-2" type="text" v-model="keyword" size="15" placeholder="검색어를 입력하세요" />
                <button class="btn btn-sm" type="button" @click="searchPlaces()"><i class="bi bi-search"></i></button>
              </div>
            </div>
            <hr />
            <ul id="placesList"></ul>
            <div id="pagination" style="margin: 10px auto; text-align: center"></div>
          </div>
        </div>
      </div>
      <div class="col-2" style="background-color: #fafafa">
        <div class="my-3 mx-3">
          <input class="form-control p-2 mb-3" type="text" placeholder="여행 계획 제목" v-model="planTitle" />
          <a-range-picker :disabled-date="disabledDate" :show-time="false" v-model="dateRange" format="YYYY.MM.DD" separator="-" />
          <div v-if="dateRange.length > 0">
            <div class="accordion my-2" id="accordionPanelsStayOpenExample">
              <div class="accordion-item">
                <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                  <button
                    class="accordion-button fs-6 p-2 fw-bold"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target="#panelsStayOpen-collapseOne"
                    aria-expanded="true"
                    aria-controls="panelsStayOpen-collapseOne"
                  >
                    출발 시간
                  </button>
                </h2>
                <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                  <div class="accordion-body">
                    <div class="my-1 ms-2" v-for="i in calculateDayCount()" :key="i">
                      {{
                        dateRange[0]
                          .clone()
                          .add(i - 1, "day")
                          .format("MM/DD")
                      }}
                      :
                      <a-time-picker v-model="dateTime[i]" format="HH:mm" placeholder="출발 시간" />
                      <button class="btn btn-sm btn-primary ms-1 py-1 px-3" type="button" @click="selectPlanTime(i)">선택</button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <h6 class="card-title fs-6 fw-bold">{{ selectedIndex + 1 }} 일차</h6>
          </div>
        </div>
        <div class="rounded d-flex flex-column p-0" style="height: 500px; overflow-y: auto">
          <!-- <div v-for="(myPlan, pIndex) in myPlans" :key="pIndex"> -->
          <div>
            <div class="card p-3 m-1 mx-3" v-for="(plan, index) in myPlans[selectedIndex]" :key="plan.place.id">
              <div class="card-body" @click="movePlacePosition(plan.selectMarker, plan.place, plan.placePosition)">
                <div class="d-flex justify-content-between">
                  <h6 class="card-title fs-6 fw-bold"><i :class="'fs-6 fw-bold bi bi-' + (index + 1) + '-square'"></i> {{ plan.place.place_name }}</h6>
                  <button type="button" class="btn-close" aria-label="Close" @click="removePlan(plan, index)"></button>
                </div>
                <input class="form-control mt-2 p-2" type="text" id="stayTimeInput" placeholder="머물 시간" v-model="plan.stayTime" />
                <a-time-picker v-model="plan.stayTime" format="HH:mm" placeholder="관광 시간" :picker-options="pickerOptions" />
              </div>
            </div>
          </div>
        </div>
        <div class="mt-auto p-2">
          <button class="btn btn-primary p-2" type="button" @click="sendMyPlan" style="width: 100%; font-size: 15px">내 여행 일정 생성</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "axios";
import moment from "moment";
import { DatePicker, TimePicker } from "ant-design-vue";
import "ant-design-vue/dist/antd.css";

export default {
  name: "KakaoMap",
  components: {
    "a-range-picker": DatePicker.RangePicker,
    "a-time-picker": TimePicker,
  },
  data() {
    return {
      map: null,
      ps: null,
      infowindow: null,
      keyword: "",
      positions: [],
      markers: [],
      myPlans: [],
      selectMarkers: [],
      planTitle: "",
      dateRange: [],
      dateTime: [],
      selectedIndex: 0,
      pickerOptions: {
        start: "00:00", // 처음 시간을 '00:00'으로 설정
      },
    };
  },
  props: {},
  watch: {
    dateTime: {
      handler(value) {
        console.log(value[0]._d.getHours());
        console.log(value[0]._d.getMinutes());
      },
      deep: true,
    },
  },
  created() {},
  mounted() {
    // api 스크립트 소스 불러오기 및 지도 출력
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }
  },
  methods: {
    // api 불러오기
    loadScript() {
      const script = document.createElement("script");
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=" + "308a4ca7d5a55fd0776a21c032574dbe" + "&autoload=false&libraries=services";
      /* global kakao */
      script.onload = () => window.kakao.maps.load(this.loadMap);

      document.head.appendChild(script);
    },
    // 맵 출력하기
    loadMap() {
      const container = document.getElementById("map");
      const options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      this.map = new window.kakao.maps.Map(container, options);
      this.infowindow = new kakao.maps.CustomOverlay({
        map: this.map,
      });
      //   this.loadMaker();

      this.ps = new kakao.maps.services.Places();

      // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
      const zoomControl = new window.kakao.maps.ZoomControl();
      this.map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

      // 키워드로 장소를 검색합니다
      // this.searchPlaces();
    },

    // 키워드 검색을 요청하는 함수입니다
    searchPlaces() {
      // var keyword = document.getElementById("keyword").value;

      if (!this.keyword.replace(/^\s+|\s+$/g, "")) {
        alert("키워드를 입력해주세요!");
        return false;
      }
      // 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
      this.ps.keywordSearch(this.keyword, this.placesSearchCB);
    },

    // 키워드 검색 완료 시 호출되는 콜백함수
    placesSearchCB(data, status, pagination) {
      if (status === window.kakao.maps.services.Status.OK) {
        // 검색 목록과 마커를 표출합니다
        this.displayPlaces(data);

        // 페이지 번호를 표출합니다
        this.displayPagination(pagination);
      } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다.");
        return;
      } else if (status === kakao.maps.services.Status.ERROR) {
        alert("검색 결과 중 오류가 발생했습니다.");
        return;
      }
    },

    // 검색 결과 목록과 마커를 표출하는 함수입니다
    displayPlaces(places) {
      let listEl = document.getElementById("placesList"),
        menuEl = document.getElementById("menu_wrap"),
        fragment = document.createDocumentFragment(),
        bounds = new window.kakao.maps.LatLngBounds();
      // listStr = "";

      // 검색 결과 목록에 추가된 항목들을 제거합니다
      this.removeAllChildNods(listEl);

      // 지도에 표시되고 있는 마커를 제거합니다
      this.removeMarker();

      for (var i = 0; i < places.length; i++) {
        // 마커를 생성하고 지도에 표시합니다
        var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
          marker = this.addMarker(placePosition, i),
          itemEl = this.getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
        // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
        // LatLngBounds 객체에 좌표를 추가합니다
        bounds.extend(placePosition);

        // 마커와 검색결과 항목에 mouseover 했을때
        // 해당 장소에 인포윈도우에 장소명을 표시합니다
        // mouseout 했을 때는 인포윈도우를 닫습니다
        const $this = this;
        (function (marker, place, placePosition) {
          // console.log(marker + title);
          kakao.maps.event.addListener(marker, "mouseover", function () {
            $this.displayInfowindow(marker, place, placePosition);
          });
          kakao.maps.event.addListener(marker, "mouseout", function () {
            $this.infowindow.setMap(null);
          });
          kakao.maps.event.addListener(marker, "click", function () {
            console.log($this.myPlans.length);
            if ($this.myPlans.length === 0) {
              alert("날짜와 시간을 지정하고 선택 버튼을 눌러주세요");
              return;
            }
            if ($this.myPlans[$this.selectedIndex].length >= 7) {
              alert("여행 계획은 최대 7개까지 가능합니다.");
              return;
            }
            // 체크 로직 추가
            const selectedPlaceId = place.id;
            const isPlaceAlreadyAdded = $this.myPlans[$this.selectedIndex].some(function (plan) {
              return plan.place.id === selectedPlaceId;
            });
            if (isPlaceAlreadyAdded) {
              alert("이미 선택한 여행 장소입니다.");
              return;
            }
            const selectMarker = $this.addSelectMarker(placePosition);
            $this.showCard(selectMarker, place, placePosition, "");
          });
          itemEl.onmouseover = function () {
            $this.displayInfowindow(marker, place, placePosition);
          };
          itemEl.onmouseout = function () {
            $this.infowindow.setMap(null);
          };
          itemEl.onclick = function () {
            $this.movePlacePosition(marker, place, placePosition);
          };
          itemEl.querySelector("#planAddButton").addEventListener("click", function () {
            console.log($this.myPlans.length);
            if ($this.myPlans.length === 0) {
              alert("날짜와 시간을 지정하고 선택 버튼을 눌러주세요");
              return;
            }
            if ($this.myPlans[$this.selectedIndex].length >= 7) {
              alert("여행 계획은 최대 7개까지 가능합니다.");
              return;
            }
            // 체크 로직 추가
            const selectedPlaceId = place.id;
            const isPlaceAlreadyAdded = $this.myPlans[$this.selectedIndex].some(function (plan) {
              return plan.place.id === selectedPlaceId;
            });
            if (isPlaceAlreadyAdded) {
              alert("이미 선택한 여행 장소입니다.");
              return;
            }
            const selectMarker = $this.addSelectMarker(placePosition);
            $this.showCard(selectMarker, place, placePosition, "");
          });
          // });
        })(marker, places[i], placePosition);
        // console.log(marker);

        fragment.appendChild(itemEl);
      }

      // 검색결과 항목들을 검색결과 목록 Element에 추가합니다
      listEl.appendChild(fragment);
      menuEl.scrollTop = 0;

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      this.map.setBounds(bounds);
    },

    // 검색결과 항목을 Element로 반환하는 함수입니다
    getListItem(index, places) {
      var el = document.createElement("li"),
        itemStr =
          '<span class="markerbg marker_' +
          (index + 1) +
          '"px;"></span>' +
          "<div style='display: flex; align-items: center; justify-content: space-between;'>" +
          "   <h6 style='font-weight: bold; text-overflow: elipsis; overflow: hidden; white-space: nowrap; padding: 5px 0 0 0'><i class='bi bi-geo-alt-fill p-1' style='color: #0d42ff'></i>" +
          places.place_name +
          "</h6><i class='bi bi-plus-square' id='planAddButton'></i></div>";

      if (places.road_address_name) {
        itemStr +=
          "    <div><span style='display: block; margin-top: 4px;'>" +
          places.road_address_name +
          "</span>" +
          '   <span style="display: block; margin-top: 4px; color: #8a8a8a; padding-left: 26px; background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;">' +
          places.address_name +
          "</span>";
      } else {
        itemStr += "    <span style='display: block; margin-top: 4px;'>" + places.address_name + "</span>";
      }

      itemStr += '  <span style="display: block; margin-top: 4px; color: #009900;" class="tel">' + places.phone + "</span>" + "</div>";

      el.innerHTML = itemStr;
      el.className = "item p-2";
      el.style = "position: relative; border-bottom: 1px solid #888; overflow: hidden; cursor: pointer; min-height: 65px;list-style: none;";

      return el;
    },

    // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
    addMarker(position, idx) {
      const imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png", // 마커 이미지 url, 스프라이트 이미지를 씁니다
        imageSize = new kakao.maps.Size(36, 37), // 마커 이미지의 크기
        imgOptions = {
          spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
          spriteOrigin: new kakao.maps.Point(0, idx * 46 + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
          offset: new kakao.maps.Point(13, 37), // 마커 좌표에 일치시킬 이미지 내에서의 좌표
        },
        markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
        marker = new kakao.maps.Marker({
          position: position, // 마커의 위치
          image: markerImage,
        });

      marker.setMap(this.map); // 지도 위에 마커를 표출합니다
      this.markers.push(marker); // 배열에 생성된 마커를 추가합니다

      return marker;
    },

    addSelectMarker(placePosition) {
      var selectMarker = new kakao.maps.Marker({
        position: placePosition,
      });
      selectMarker.setMap(this.map); // 지도 위에 마커 표출
      this.selectMarkers.push(selectMarker); // 배열에 생성된 마커 추가
      console.log("선택된 마커들");
      console.log(selectMarker);
      console.log(this.selectMarkers);
      return selectMarker;
    },

    removeMarker() {
      for (var i = 0; i < this.markers.length; i++) {
        this.markers[i].setMap(null);
      }
      this.markers = [];
    },

    removeSelectMarker(plan) {
      console.log("마커 지우자!");
      const index = this.selectMarkers.findIndex((marker) => marker === plan.selectMarker);
      console.log(this.selectMarkers[0] === plan.selectMarker);
      console.log(this.selectMarkers[0]);
      console.log(plan.selectMarker);
      console.log("인덱스결과!");
      console.log(index);
      if (index !== -1) {
        let marker = this.selectMarkers[index];
        console.log(marker);
        this.selectMarkers.splice(index, 1);
        console.log(this.selectMarkers);
        marker.setMap(null); // 마커 제거
      }
    },

    // 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
    displayPagination(pagination) {
      var paginationEl = document.getElementById("pagination"),
        fragment = document.createDocumentFragment(),
        i;

      // 기존에 추가된 페이지번호를 삭제합니다
      while (paginationEl.hasChildNodes()) {
        paginationEl.removeChild(paginationEl.lastChild);
      }

      for (i = 1; i <= pagination.last; i++) {
        var el = document.createElement("a");
        el.href = "#";
        el.innerHTML = i;
        el.className = "btn btn-sm bnt-primary";
        el.style = "display: inline-block; margin-right: 10px;";

        if (i === pagination.current) {
          el.className = "on btn btn-sm";
          el.style = "font-weight: bold; cursor: default; color: #777; margin-right: 10px";
        } else {
          el.onclick = (function (i) {
            return function () {
              pagination.gotoPage(i);
            };
          })(i);
        }

        fragment.appendChild(el);
      }
      paginationEl.appendChild(fragment);
    },

    // 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
    // 인포윈도우에 장소명을 표시합니다
    displayInfowindow(marker, place, placePosition) {
      const content =
        '<div class="card" style="margin-bottom: 150px">' +
        '  <div class="card-body">' +
        '    <div class="d-flex justify-content-between">' +
        '        <h8 class="card-title">장소명: ' +
        place.place_name +
        "</h8>" +
        "    </div>" +
        '    <h8 class="card-subtitle mb-2 text-muted">주소: ' +
        place.address_name +
        "</h8>" +
        "  </div>" +
        "</div>";

      this.infowindow.setContent(content);
      this.infowindow.setPosition(marker.getPosition());
      this.infowindow.setMap(this.map);
    },

    showCard(selectMarker, place, placePosition, stayTime) {
      this.myPlans[this.selectedIndex].push({ selectMarker, place, placePosition, stayTime });
      console.log(selectMarker + " " + place.place_name + " " + placePosition + " " + stayTime);
      console.log(this.myPlans[this.selectedIndex]);
      this.updateComponent();
    },
    updateComponent() {
      // 컴포넌트를 업데이트하기 위해 빈 객체를 할당한 후 다시 myPlans과 selectedIndex 값을 할당합니다.
      // 이를 통해 Vue는 변경을 감지하고 컴포넌트를 리렌더링합니다.
      this.myPlans = [...this.myPlans];
      this.selectedIndex = this.selectedIndex;
    },

    removeAllChildNods(el) {
      while (el.hasChildNodes()) {
        el.removeChild(el.lastChild);
      }
    },
    removePlan(plan, planIndex) {
      // 여행 계획 삭제
      if (this.myPlans[this.selectedIndex] && this.myPlans[this.selectedIndex][planIndex]) {
        this.myPlans[this.selectedIndex].splice(planIndex, 1);
      }
      // const index = this.myPlan.indexOf(plan);
      // console.log("plan");
      // console.log(plan);
      // if (index !== -1) {
      //   this.myPlan.splice(index, 1);
      // }
      // console.log("지워지는 객체는!");
      // console.log(plan);
      this.removeSelectMarker(plan);
    },

    movePlacePosition(selectMarker, place, placePosition) {
      this.map.setCenter(placePosition);
      this.displayInfowindow(selectMarker, place, placePosition);
    },
    selectPlanTime(index) {
      console.log("선택된 시간 인덱스는!! " + index);
      this.selectedIndex = index - 1;
      console.log(this.dateTime[index]);
      // 선택된 출발 시간에 따라 여행 계획 저장
      // const selectedTime = this.dateTime[this.selectedIndex];
      // const selectedDate = this.dateRange[0].clone().add(index - 1, "day");

      // const plan = {
      //   place: {}, // 여행 장소 정보
      //   selectMarker: null, // 선택한 마커
      //   placePosition: null, // 장소의 위치
      //   stayTime: "", // 머물 시간
      // };

      // // 여행 계획에 출발 시간과 날짜를 저장
      // plan.place.selectedTime = selectedTime;
      // plan.place.selectedDate = selectedDate;

      // 해당 일자에 계획 추가
      if (!this.myPlans[this.selectedIndex]) {
        this.myPlans[this.selectedIndex] = [];
      }
      // this.myPlans[this.selectedIndex].push(plan);
    },

    sendMyPlan() {
      const REST_API_KEY = "718049a1e16b0994e3ed033e857244a3";
      http.defaults.headers.common["Authorization"] = `KakaoAK ${REST_API_KEY}`;
      http.defaults.headers.common["Content-Type"] = "application/json";

      for (let i = 0; i < this.myPlans.length; i++) {
        // 현재 요소에 대한 작업 수행
        console.log(i + 1 + "번 요청!!!!!");
        // console.log(this.myPlans[i]);
        // console.log(
        //   this.dateRange[0].clone().add(i, "day").format("YYYYMMDD") +
        //     this.dateTime[i + 1]._d.getHours().toString().padStart(2, "0") +
        //     this.dateTime[i + 1]._d.getMinutes().toString().padStart(2, "0")
        // );

        let drivingTimeAPI =
          "https://apis-navi.kakaomobility.com/v1/future/directions?" +
          "origin=" +
          this.myPlans[i][0].placePosition.La +
          "," +
          this.myPlans[i][0].placePosition.Ma +
          "&destination=" +
          this.myPlans[i][this.myPlans[i].length - 1].placePosition.La +
          "," +
          this.myPlans[i][this.myPlans[i].length - 1].placePosition.Ma +
          "&departure_time=" +
          this.dateRange[0].clone().add(i, "day").format("YYYYMMDD") +
          this.dateTime[i + 1]._d.getHours().toString().padStart(2, "0") +
          this.dateTime[i + 1]._d.getMinutes().toString().padStart(2, "0");

        if (this.myPlans[i].length > 2) {
          // 경유지가 있으면
          drivingTimeAPI += "&waypoints=";
          for (let j = 1; j < this.myPlans[i].length - 1; j++) {
            const wayPointLa = this.myPlans[i][j].placePosition.La;
            const wayPointMa = this.myPlans[i][j].placePosition.Ma;
            drivingTimeAPI += wayPointLa + "," + wayPointMa;
            if (j != this.myPlans[i].length - 2) {
              drivingTimeAPI += "|";
            }
          }
        }
        console.log(drivingTimeAPI);

        http
          .get(drivingTimeAPI)
          .then((response) => {
            console.log(response.data.routes[0].sections[0].distance, response.data.routes[0].sections[0].duration);
            alert(
              "이동 거리: " +
                response.data.routes[0].sections[0].distance / 1000 +
                "km   " +
                "소요 시간: " +
                Math.floor(response.data.routes[0].sections[0].duration / 60) +
                "분 " +
                (response.data.routes[0].sections[0].duration % 60) +
                "초"
            );
          })
          .catch((error) => {
            console.log(error);
          });
      }
      // AJAX 요청 등을 통해 jsonData를 특정 API에 전송
      // 예를 들어 axios를 사용한 POST 요청 예시:
      // http
      //   .post("https://example.com/api/endpoint", jsonData)
      //   .then((response) => {
      //     // 요청에 대한 성공적인 응답 처리
      //   })
      //   .catch((error) => {
      //     // 요청에 대한 오류 처리
      //   });
    },
    moment,
    range(start, end) {
      const result = [];
      for (let i = start; i < end; i++) {
        result.push(i);
      }
      return result;
    },
    disabledDate(current) {
      // Can not select days before today and today
      return current && current < moment().endOf("day");
    },
    calculateDayCount() {
      if (this.dateRange.length === 2) {
        const start = this.dateRange[0].clone().startOf("day");
        const end = this.dateRange[1].clone().startOf("day");
        const duration = moment.duration(end.diff(start));
        return duration.asDays() + 1; // 일 수 계산 후 1을 더해줍니다.
      }
      return 0;
    },
  },
};
</script>

<style scoped>
#map {
  width: 100%;
  height: 700px;
}
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap a,
.map_wrap a:hover,
.map_wrap a:active {
  color: #000;
  text-decoration: none;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 700px;
}
#menu_wrap {
  position: absolute;
  top: 0;
  left: 0;
  bottom: 0;
  width: 250px;
  margin: 10px 0 30px 10px;
  padding: 5px;
  overflow-y: auto;
  background: rgba(255, 255, 255, 0.7);
  z-index: 1;
  font-size: 12px;
  border-radius: 10px;
}
.bg_white {
  background: #fff;
}
#menu_wrap hr {
  display: block;
  height: 1px;
  border: 0;
  border-top: 2px solid #5f5f5f;
  margin: 3px 0;
}
#menu_wrap .option {
  text-align: center;
}
#menu_wrap .option p {
  margin: 10px 0;
}
#menu_wrap .option button {
  margin-left: 5px;
}
#placesList li {
  list-style: none;
}
#placesList .item {
  position: relative;
  border-bottom: 1px solid #888;
  overflow: hidden;
  cursor: pointer;
  min-height: 65px;
}
#placesList .item span {
  display: block;
  margin-top: 4px;
}
#placesList .item h5,
#placesList .item .info {
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
  padding: 10px 0 10px 55px;
}
#placesList .info .gray {
  color: #8a8a8a;
}
#placesList .info .jibun {
  padding-left: 26px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/places_jibun.png) no-repeat;
}
#placesList .info .tel {
  color: #009900;
}
#placesList .item .markerbg {
  float: left;
  position: absolute;
  width: 36px;
  height: 37px;
  margin: 10px 0 0 10px;
  background: url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png) no-repeat;
}

#placesList .item .marker_1 {
  background-position: 0 -10px;
}
#placesList .item .marker_2 {
  background-position: 0 -56px;
}
#placesList .item .marker_3 {
  background-position: 0 -102px;
}
#placesList .item .marker_4 {
  background-position: 0 -148px;
}
#placesList .item .marker_5 {
  background-position: 0 -194px;
}
#placesList .item .marker_6 {
  background-position: 0 -240px;
}
#placesList .item .marker_7 {
  background-position: 0 -286px;
}
#placesList .item .marker_8 {
  background-position: 0 -332px;
}
#placesList .item .marker_9 {
  background-position: 0 -378px;
}
#placesList .item .marker_10 {
  background-position: 0 -423px;
}
#placesList .item .marker_11 {
  background-position: 0 -470px;
}
#placesList .item .marker_12 {
  background-position: 0 -516px;
}
#placesList .item .marker_13 {
  background-position: 0 -562px;
}
#placesList .item .marker_14 {
  background-position: 0 -608px;
}
#placesList .item .marker_15 {
  background-position: 0 -654px;
}
#pagination {
  margin: 10px auto;
  text-align: center;
}
#pagination a {
  display: inline-block;
  margin-right: 10px;
}
#pagination .on {
  font-weight: bold;
  cursor: default;
  color: #777;
}
/* infowindow css */
.wrap {
  position: absolute;
  left: 0;
  bottom: 40px;
  width: 288px;
  height: 132px;
  margin-left: -144px;
  text-align: left;
  overflow: hidden;
  font-size: 12px;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  line-height: 1.5;
}
.wrap * {
  padding: 0;
  margin: 0;
}
.wrap .info {
  width: 286px;
  height: 120px;
  border-radius: 5px;
  border-bottom: 2px solid #ccc;
  border-right: 1px solid #ccc;
  overflow: hidden;
  background: #fff;
}
.wrap .info:nth-child(1) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.info .title {
  padding: 5px 0 0 10px;
  height: 30px;
  background: #eee;
  border-bottom: 1px solid #ddd;
  font-size: 18px;
  font-weight: bold;
}
.info .close {
  position: absolute;
  top: 10px;
  right: 10px;
  color: #888;
  width: 17px;
  height: 17px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png");
}
.info .close:hover {
  cursor: pointer;
}
.info .body {
  position: relative;
  overflow: hidden;
}
.info .desc {
  position: relative;
  margin: 13px 0 0 90px;
  height: 75px;
}
.desc .ellipsis {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.desc .jibun {
  font-size: 11px;
  color: #888;
  margin-top: -2px;
}
.info .img {
  position: absolute;
  top: 6px;
  left: 5px;
  width: 73px;
  height: 71px;
  border: 1px solid #ddd;
  color: #888;
  overflow: hidden;
}
.info:after {
  content: "";
  position: absolute;
  margin-left: -12px;
  left: 50%;
  bottom: 0;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}
.info .link {
  color: #5085bb;
}
</style>
