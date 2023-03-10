<template>
    <div class="sidenavbar"  :class="{ 'is-primary': islogin }">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css" />


        <nav class="nav-link" :class="`${is_expanded ? 'is-expanded' : ''}`">

            <ul :class="`${is_expanded ? 'toggle-nav-item' : 'show-full-nav-item'}`">
                <div class="toggle-menu" @click="ToggleMenu">
                    <i :class="`${is_expanded ? 'fa-solid fa-bars btn-open' : 'fa-solid fa-close btn-close'}`"></i>
                </div>

                <router-link to="/profile">
                    <li :class="{ active: activeTab === 0 }"><i class="fa-solid fa-house icon"></i>
                        <span>HOME</span>
                    </li>
                    <!-- <i class="fa-brands fa-facebook-messenger" ></i> -->
                </router-link>
                <!-- <router-link to="/"> <i class="fa-brands fa-facebook-messenger" ></i>
                 </router-link> -->

                <!-- <a href="/profile">
                    <li :class="{ active: activeTab === 0 }"><i class="fa-solid fa-house icon"></i>
                        <span>HOME</span>
                    </li>
                </a> -->
                <a href="#about">
                    <li :class="{ active: activeTab === 1 }"><i class="fa-solid fa-comments icon"></i> <span>CHAT</span>
                    </li>
                </a>
                <router-link to="/friends">
                    <li :class="{ active: activeTab === 2 }"><i class="fa-solid fa-users icon"></i> <span>FRIENDS</span>
                    </li>
                </router-link>
                <footer><router-link to="/profile/1" class="side-profile">
                    <li :class="{ active: activeTab === 3 }"><img
                            src="@/assets/Profile_photo.png"><i ></i> <span></span>
                    </li>
                </router-link>
                <!-- <router-link to="/profile">
                    <li :class="{ active: activeTab === 0 }"><i class="fa-solid fa-house icon"></i>
                        <span>HOME</span>
                    </li> -->
                    <!-- <i class="fa-brands fa-facebook-messenger" ></i> -->
                <!-- </router-link> -->
            </footer>
                <!-- <footer>
                    <a href="#project">
                        <li><i class="fa-solid fa-sign-out icon"></i> <span>SIGN-OUT</span></li>
                    </a>
                </footer> -->
            </ul>

        </nav>

    </div>
</template>


<script setup>

let islogin = "http://localhost:8081/" === window.location.href;
    console.log(islogin)



</script>


<script>
export default {
    data() {
        return {
            activeTab: 0,
            is_expanded: true,
        };
    },
    methods: {
        ToggleMenu() {
            this.is_expanded = !this.is_expanded;
            this.$emit('toggle-menu', this.is_expanded);
        },
    },
//     methods: {
//     ToggleMenu() {
//       this.$emit('toggle-menu', !this.is_expanded);
//     }
//   },
};


</script>

<style lang="scss" scoped>
/* font family google api */
@import url("https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap");

/* reset all style */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    list-style: none;
    text-decoration: none;
}

/* set html & body style */
html,
body {
    font-weight: normal;
    font-style: normal;
    line-height: 1.65;
    scroll-behavior: smooth;
    font-family: "Roboto", sans-serif;
    color: rgb(26, 27, 27);
}



/* menyembunyikan konten yang melebihi luas body */
body {
    overflow-x: hidden;
}

a {
    color: rgb(186, 187, 187);
}

i {
    cursor: pointer;
}


/* brand-logo style */

.show-full-nav-item span {

    font-size: medium;
    font-style: oblique;

}

.toggle-nav-item span {


    color: rgb(26, 27, 27);
    font-size: medium;
    font-style: oblique;

}


/* toggle-menu style */
.toggle-menu {
    justify-content: center;
    height: 50px;
    border-radius: 0px;
    font-size: 1.5rem;
    display: flex;
    align-items: center;
    transition: all 0.3s;
    color: rgb(186, 187, 187);

}

.toggle-menu i .btn-close {
    position: absolute;
}




.toggle-menu .btn-close:hover {
    justify-content: flex-start;
    transition: all 0.3s;
}

.toggle-menu:hover {
    border-radius: 10%;
    color: white;
}

/* tampilan navigasi sebelum humberger menu di klik */
.toggle-nav-item {
    width: 100px;
    margin: 0px;
    height: 100%;
    padding: 0.6em;
    position: fixed;
    left: 0;
    z-index: 1;
    background-color: rgb(26, 27, 27);
    transition: width 0.3s ease-out;
    max-height: 100vh;
    position: fixed;
    top: 0;

}



.toggle-nav-item a li {
    padding: 1em;
    border-radius: 10px;
    font-size: 0.7rem;
    text-align: center;
    display: flex;
    flex-direction: column;
    transition: all 0.3s;
    color: dimgray;

}

.toggle-nav-item i {
    font-size: 1.4rem;
    margin-bottom: 0.15em;
    transition: all 0.3s;
}

.toggle-nav-item a li:hover {
    
    color: black;
    transition: all 0.3s;

}

.toggle-nav-item li.active {
    color: white;
}

/* tampilan navigasi saat humberger menu sudah di klik */

.show-full-nav-item {
    color: dimgray;
    width: 130px;
    margin: 0px;
    height: 100%;
    position: fixed;
    left: 0;
    z-index: 1;
    background-color: rgb(26, 27, 27);
    transition: width 0.3s ease-out;

}

.show-full-nav-item li.active {
    text-transform: capitalize;
    color: white;
}

.show-full-nav-item a li {
    padding: 1.25em;
    border-radius: 10px;
    display: flex;
    transition: all 0.3s;
}

.show-full-nav-item a li:hover {
    padding-left: 2em;
    color: white;
    transition: all 0.3s;
}



.show-full-nav-item i {
    z-index: 1;
    margin-right: 1em;
    transition: all 0.3s;
}

/* class tambahan untuk mengubah style display:none pada class full-nav-item */


/* wrapper-header style */
.wrapper-header {
    width: 100%;
    padding-left: 2em;
    padding-right: 6.4em;
    position: absolute;
    top: 60px;
    left: 4.4em;
    display: flex;
    flex-direction: column;
    align-items: center;
    transition: all 0.1s;
}

/* class tambahan untuk mengubah posisi content saat area navbar dalam posisi melebar */
.swipe-wrapper-header {
    width: 80%;
    left: 20%;
    padding-right: 2em;
    transition: all 0.15s ease 0.15s;
}

/* wrapper-header section style */
.wrapper-header section {
    width: 100%;
    min-height: 500px;
    padding: 0 1.5em;
    padding-top: 5em;
    margin-top: 2em;
    border-radius: 10px;
    text-align: center;
    background-color: rgb(212, 154, 45);
    transition: all 0.3s;
}

.wrapper-header section h1 {
    margin-bottom: 1em;
}

.wrapper-header section h1::after {
    content: "";
    display: block;
    width: 5%;
    margin: auto;
    border-bottom: 2px solid rgb(26, 27, 27);
}

/* footer style */
footer {
    font-size: medium;
    width: 100%;
    padding: 0.5em;
    margin-top: 10em;

}

.toggle-nav-item .side-profile img{
    margin-top: 21em !important;
    padding: 0%;
    margin: 0%;
    display: flex;
    max-width: 50px;
    border-radius: 50%;
    flex-direction: row;
}

.show-full-nav-item .side-profile img{
    margin-left: .5em !important;
    margin-top: 16em !important;
    padding: 0%;
    margin: 0%;
    display: flex;
    max-width: 50px;
    border-radius: 50%;
    flex-direction: row;

}



.toggle-nav-item.is-expanded {
    width: 200px;
    height: 100%;
    padding: 0.6em;
    position: fixed;
    left: 0;
    z-index: 1;
    background-color: rgb(26, 27, 27);
    width: 200px;
    transition: width 0.3s ease-out;

}

icon.hover {
    color: white;
}

.icon {
    color: #F24E1E;
}

.is-primary{
    display: none;
}


.nav-link {
     padding: 0px ;
}
</style>