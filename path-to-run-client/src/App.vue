<template>
  <div class="path-to-run">
    <route-form />
    <route-map />
  </div>
</template>

<script>
import RouteForm from './components/RouteForm'
import RouteMap from './components/RouteMap'

export default {
  name: 'App',
  components: { RouteForm, RouteMap }
}
</script>

<!-- WARNING: Not scoped -->
<style lang="scss">
.path-to-run {
  display: flex;
  margin-top: 0.5em;
  border-radius: 0.35em;
  overflow: hidden;
  background-color: #f7f7f7;
  box-shadow: 10px 10px 20px -10px rgba(0,0,0,0.1);
}
</style>
