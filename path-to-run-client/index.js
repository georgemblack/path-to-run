import Vue from 'vue';
import App from './App';

/**
 * Render map
 */
mapboxgl.accessToken = 'pk.eyJ1IjoiZ2VvcmdlYmxhY2siLCJhIjoiY2p0eDlxN2gxMmd1YjQ0bnJpNjZ4dzZmNCJ9.AVI4_TOJQgcBzaLhndmyHQ';
var map = new mapboxgl.Map({
  container: 'map',
  style: 'mapbox://styles/mapbox/streets-v11'
});

/**
 * Render Vue app
 */
new Vue({
  el: '#app',
  render: h => h(App)
});
