import axios from 'axios';

// 配置接口地址
axios.defaults.baseURL = 'http://localhost:7101';

// 响应时间
axios.defaults.timeout = 3 * 1000;

// 配置cookie
axios.defaults.withCredentials = false;

// 静态资源
// Vue.prototype.$static = '';

// var loadingInstance;
// POST传参序列化(添加请求拦截器)
//
// 返回状态判断(添加响应拦截器)
// axios.interceptors.response.use(
//     res => {
//       if (res.data.code === 200) {
//         loadingInstance.close();
//         return res;
//       } else {
//         loadingInstance.close();
//         Message.error(res.data.msg);
//       }
//     },
//     err => {
//       loadingInstance.close();
//       Message.error('请求失败，请稍后再试');
//       return Promise.reject(err);
//     },
// );

// 发送请求
// export function fetchPost(url, params) {
//   return new Promise((resolve, reject) => {
//     axios.post(url, params).then(
//         res => {
//           resolve(res.data);
//         },
//         err => {
//           reject(err.data);
//         },
//     ).catch(err => {
//       reject(err.data);
//     });
//   });
// }
//
// export function fetchGet(url, params) {
//   return new Promise((resolve, reject) => {
//     axios.get(url, {
//       params: params,
//     }).then(res => {
//       resolve(res.data);
//     }).catch(err => {
//       reject(err.data);
//     });
//   });
// }
