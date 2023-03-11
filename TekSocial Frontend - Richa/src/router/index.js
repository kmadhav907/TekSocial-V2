import { createRouter, createWebHistory } from 'vue-router'
import ProfileDetails from '../components/ProfileDetails.vue'
import SideNav from '../components/SideNav.vue'
// import ProfileDetails from '../components/ProfileDetails.vue'
import Home from '../components/Home.vue'
import FriendsView from '../components/FriendsView.vue'
import SignupLogin from '../components/SignupLogin.vue'
import ChatView from '../components/ChatView.vue'


const routes = [
  {
    path: '/',
    name: 'SignupLogin',
    component: SignupLogin,
    
  },
  {
    path: '/friends',
    name:"FriendsView",
    component: FriendsView
  },
  {
    path: '/home',
    name: 'Home',
    component: Home,
    
  },
  {
    path: '/profile/:id',
    name: 'profile',
    component: ProfileDetails,
  },
  {
    path:"/chat",
    name:"ChatView",
    component: ChatView,
  }

 


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router


