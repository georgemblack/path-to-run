const getBaseUrl = () => {
  const base = window.location.host
  const scheme = (base.includes('localhost')) ? 'http' : 'https'
  return `${scheme}://${base}`
}

const getQueryString = (params) => {
  return '?' + Object.keys(params).map(k => k + '=' +params[k]).join('&')
}

export default {
  setStartLocation: ({ commit }, place) => {
    commit('SET_START_LOCATION', place)
  },
  getRoutes: ({ commit }, params) => {
    const base = getBaseUrl()
    const qs = getQueryString(params)
    const url = `${base}/api/routes${qs}`
    console.log(`Sending request to: ${url}`)

    fetch(url, {
      method: 'GET'
    })
      .then(response => response.json())
      .then(response => {
        console.log(response)
        commit('SET_ROUTES', response.routes)
      })
      .catch(err => {
        console.log(`There was an error with the routes request: ${err}`)
      })
  }
}
