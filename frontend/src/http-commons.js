import axios from 'axios'

export const api = function (uri) {
    return axios.create({
        baseURL: `http://localhost:4269`,
        headers: {
            'Access-Control-Allow-Origin': 'http://localhost:8080',
            'Access-Control-Allow-Headers': 'Access-Control-Allow-Origin'
        }
    }).get("api/" + uri)
}