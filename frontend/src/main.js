import { createApp } from 'vue'
import App  from './App.vue'
import router from './router'
import store from './store'
import './axios.js'
import AOS from 'aos';
import "aos/dist/aos.css";

import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

const app = createApp(App);
app.use(router);
app.use(store);
app.use(Datepicker);
app.use(AOS);
app.mount('#app','#test');

