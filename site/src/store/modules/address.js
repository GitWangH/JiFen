import api from '../../service/api'
import * as types from '../mutation-type'

const state = {
  ChinaList: ''
}

const actions = {
  // 获取用户信息
  getChinaAddress ({commit}, params) {
    api.getChinaAddress(params)
      .then(res => {
        commit(types.GET_CHINA_ADDRESS, res)
      })
  }
}

const getters = {
  ChinaList: state => state.ChinaList,
  DeliveryAddressList: state => state.DeliveryAddressList,
  DefaultAddressId: state => state.DefaultAddressId
}

const mutations = {
  // 获取用户信息
  [types.GET_CHINA_ADDRESS] (state, res) {
    state.ChinaList = res
  }
}

export default {
  state,
  getters,
  mutations,
  actions
}
