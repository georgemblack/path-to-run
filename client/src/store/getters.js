import isEmpty from 'lodash.isempty'

export default {
  startLocationLat: state => {
    if (isEmpty(state.startLocation)) return ''
    return state.startLocation.geometry.location.lat().toString()
  },
  startLocationLng: state => {
    if (isEmpty(state.startLocation)) return ''
    return state.startLocation.geometry.location.lng().toString()
  },
  startLocationExists: state => {
    return !isEmpty(state.startLocation)
  },
  region: state => state.region,
  routes: state => state.routes,
}
