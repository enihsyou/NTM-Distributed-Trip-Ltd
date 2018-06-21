import axios_ from 'axios'

const axios = axios_.create({
  headers: { 'Authorization': `Bearer ${localStorage.getItem('access_token')}` },
})

export default axios
