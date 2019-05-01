export const SET_START_LOCATION = 'SET_START_LOCATION'
export const SET_ROUTES = 'SET_ROUTES'

export default {
  [SET_START_LOCATION](state, place) {
    state.startLocation = place
  },
  [SET_ROUTES](state, route) {
    state.routes = route
  }
}
