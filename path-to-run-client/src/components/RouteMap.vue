<template>
  <div id="map" style="width: 800px; height: 600;"></div>
</template>

<script>
export default {
  name: 'RouteMap',
  mounted() {
    mapboxgl.accessToken = 'pk.eyJ1IjoiZ2VvcmdlYmxhY2siLCJhIjoiY2p0eDlxN2gxMmd1YjQ0bnJpNjZ4dzZmNCJ9.AVI4_TOJQgcBzaLhndmyHQ';
    var map = new mapboxgl.Map({
     container: 'map',
      style: 'mapbox://styles/mapbox/streets-v11'
    });
  }
}
</script>

<style lang="scss" scoped>
</style>
