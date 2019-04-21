<template>
  <div ref="map" class="route-map"></div>
</template>

<script>
export default {
  name: 'RouteMap',
  /**
   * Init Google Maps
   */
  mounted() {
    this.map = new google.maps.Map(this.$refs.map, {
      center: {lat: 37.1, lng: -95.7},
      zoom: 4,
      clickableIcons: false,
      streetViewControl: false
    });
  }
};
</script>

<style lang="scss" scoped>
.route-map {
  flex-grow: 1;
  height: 600px;
}
</style>
