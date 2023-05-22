<template>
  <div>
    <breadcrumb-section title="지역별 인기 관광지 TOP 10" description="전국 어디든, 지역별 인기 관광지를 검색해보세요!" />
    <!-- Map Section -->
    <div class="col-md-9 mx-auto container mb-5">
      <form class="d-flex my-3">
        <select id="area1List" class="form-select me-2" v-model="areaCode" @change="getArea2List(areaCode)">
          <option value="">시도를 선택하세요</option>
          <option v-for="area in area1List" :key="area.code" :value="area.code">{{ area.name }}</option>
        </select>
        <select id="area2List" class="form-select me-2" v-model="sigunguCode">
          <option value="">구군을 선택하세요</option>
          <option v-for="area in area2List" :key="area.code" :value="area.code">{{ area.name }}</option>
        </select>

        <select id="cat1List" class="form-select me-2" v-model="cat1" @change="getCat2List(cat1)">
          <option value="">대분류 선택하세요</option>
          <option v-for="cat in cat1List" :key="cat.code" :value="cat.code">{{ cat.name }}</option>
        </select>
        <select id="cat2List" class="form-select me-2" v-model="cat2" @change="getCat3List(cat2)">
          <option value="">중분류 선택하세요</option>
          <option v-for="cat in cat2List" :key="cat.code" :value="cat.code">{{ cat.name }}</option>
        </select>
        <select id="cat3List" class="form-select me-2" v-model="cat3">
          <option value="">소분류 선택하세요</option>
          <option v-for="cat in cat3List" :key="cat.code" :value="cat.code">{{ cat.name }}</option>
        </select>
        <button type="button" class="btn btn-outline-success" id="btnSearch" @click="search">검색</button>
      </form>

      <!-- kakao map start -->
      <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
      <!-- kakao map end -->

      <!-- 관광지 table -->
      <div id="table-container">
        <table class="table table-striped table-hover">
          <thead class="table-dark">
            <tr>
              <th>번호</th>
              <th>관광지</th>
              <th>주소</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item, index) in itemList" :key="item.title" :data-x="item.mapx" :data-y="item.mapy" @click="handleItemClick(index)">
              <td>{{ index + 1 }}</td>
              <td>{{ item.title }}</td>
              <td>{{ item.addr1 }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <!-- End 관광지 table -->
      <!-- kakao map start -->
      <div id="map" class="mt-3" style="width: 100%; height: 400px"></div>
      <!-- kakao map end -->
    </div>
    <!-- End Map Section -->
  </div>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import http from '@/common/axios'
// import key from '../common/key'
// import MainCss from '../common/js/main'

export default {
  components: {
    BreadcrumbSection,
  },
  data() {
    return {
      areaCode: "",
      sigunguCode: "",
      cat1: "",
      cat2: "",
      cat3: "",
      positions: [],
      area1List: [],
      area2List: [],
      cat1List: [],
      cat2List: [],
      cat3List: [],
      itemList: [],
      map: null,
      markers: [],
    };
  },
  async mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap();
    } else {
      this.loadScript();
    }

    this.getAreaCodeList();
    this.getCat1List();
  },
  methods: {
    loadScript() {
      const script = document.createElement("script");
      script.type = "text/javascript";
      script.src = "//dapi.kakao.com/v2/maps/sdk.js?appkey=308a4ca7d5a55fd0776a21c032574dbe&autoload=false";
      script.onload = () => window.kakao.maps.load(this.loadMap);
      document.head.appendChild(script);
    },
    loadMap() {
      const mapContainer = document.getElementById("map"); // 지도를 표시할 div
      const mapOption = {
        center: new window.kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
        level: 3,
      };
      this.map = new window.kakao.maps.Map(mapContainer, mapOption);
    },
    getAreaCodeList() {
      http
        .get("/trip/area?areaCode=")
        .then((response) => {
          this.area1List = response.data.response.body.items.item;
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getArea2List(areaCode) {
      http
        .get("/trip/area", {
          params: {
            areaCode: areaCode,
          },
        })
        .then((response) => {
          this.sigunguCode = "";
          this.area2List = response.data.response.body.items.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCat1List() {
      http
        .get("/trip/category?cat1=&cat2=")
        .then((response) => {
          this.cat1List = response.data.response.body.items.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCat2List(cat1) {
      http
        .get("/trip/category", {
          params: {
            cat1: cat1,
            cat2: "",
          },
        })
        .then((response) => {
          this.cat2 = "";
          this.cat3 = "";
          this.cat2List = response.data.response.body.items.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getCat3List(cat2) {
      http
        .get("/trip/category", {
          params: {
            cat1: this.cat1,
            cat2: cat2,
          },
        })
        .then((response) => {
          this.cat3 = "";
          this.cat3List = response.data.response.body.items.item;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    search() {
      http
        .get("/trip/list", {
          params: {
            areaCode: this.areaCode,
            sigunguCode: this.sigunguCode,
            cat1: this.cat1,
            cat2: this.cat2,
            cat3: this.cat3,
            numOfRows: 10,
            pageNo: 1,
          },
        })
        .then((response) => {
          this.itemList = response.data.response.body.items.item;
          this.updateMarkers();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    updateMarkers() {
      this.clearMarkers();
      const bounds = new window.kakao.maps.LatLngBounds();
      for (const item of this.itemList) {
        const position = new window.kakao.maps.LatLng(item.mapy, item.mapx);
        const marker = new window.kakao.maps.Marker({
          position: position,
        });
        marker.setMap(this.map);
        bounds.extend(position);
        this.markers.push(marker);
      }
      this.map.setBounds(bounds);
    },
    clearMarkers() {
      for (const marker of this.markers) {
        marker.setMap(null);
      }
      this.markers = [];
    },
    makeList(data) {
      let trips = data.response.body.items.item;
      this.positions = [];
      trips.forEach((area) => {
        let markerInfo = {
          title: area.title,
          latlng: new window.kakao.maps.LatLng(area.mapy, area.mapx),
        };
        this.positions.push(markerInfo);
      });
      this.displayMarker();
    },
  },
};
</script>
