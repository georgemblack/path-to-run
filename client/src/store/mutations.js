export const SET_START_LOCATION = 'SET_START_LOCATION'
export const SET_ROUTES = 'SET_ROUTES'
export const SET_REGION = 'SET_REGION'

export default {
  [SET_START_LOCATION](state, payload) {
    state.startLocation = payload
  },
  [SET_ROUTES](state, payload) {
    state.routes = payload
  },
  [SET_REGION](state, payload) {
    state.region = [payload.southwest, payload.northeast]
  }
}
