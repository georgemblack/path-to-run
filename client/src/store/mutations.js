const SET_START_LOCATION = 'SET_START_LOCATION'
const SET_ROUTES = 'SET_ROUTES'

export default {
  [SET_START_LOCATION](state, place) {
    state.startLocation = place
  },
  [SET_ROUTES](state, routes) {
    state.routes = routes
  }
}
