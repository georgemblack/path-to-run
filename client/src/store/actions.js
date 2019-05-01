import '@babel/polyfill'
import {
  SET_START_LOCATION,
  SET_ROUTES
} from './mutations'

const getBaseUrl = () => {
  const base = window.location.host
  const scheme = (base.includes('localhost')) ? 'http' : 'https'
  return `${scheme}://${base}`
}

const getQueryString = (params) => {
  return '?' + Object.keys(params).map(k => k + '=' +params[k]).join('&')
}

export default {
  setStartLocation: async ({ commit }, place) => {
    commit(SET_START_LOCATION, place)
  },
  getRoutes: async ({ commit }, params) => {

    const base = getBaseUrl()
    const qs = getQueryString(params)
    const url = `${base}/api/routes${qs}`
    console.log(`Sending request to: ${url}`)

    let response = await fetch(url, {
      method: 'GET'
    })

    let responseBody = await response.json()

    // TODO: error handling

    commit(SET_ROUTES, responseBody.routes)
  }
}
