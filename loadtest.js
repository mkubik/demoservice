import http from 'k6/http';

import { sleep } from 'k6';


export default function () {

  http.get('http://localhost:8080/request?time=500');

  sleep(1);

}