<template>
  <div class="map_wrap">
    <breadcrumb-section title="Trip Plan" description="여행을 미리 계획해보세요" />
    <kakao-map></kakao-map>
  </div>
</template>

<script>
import BreadcrumbSection from "@/components/BreadcrumbSection.vue";
import KakaoMap from "@/components/TheKakaoMap.vue";

export default {
  components: {
    BreadcrumbSection,
    KakaoMap,
  },
};
</script>

<style></style>
