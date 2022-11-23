<template>
<div>
    <h1>Test</h1>
    Test
    <button @click="methodA">Naver</button>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    data() {},
    methods: {
        naverLoginBtn() {
            var client_id = 'z_xevkfqoAuqghG2b8CF';
            var callbackUrl = 'http://localhost:8081/Test'; //서버 주소
            var url = 'https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=' + client_id + '&redirect_uri=' + callbackUrl + '&state=1234';
            window.location.replace(url);
        },
        methodA() {
          console.log(1)
          return this.methodB();
        },
        methodB() {
          console.log(2)

        },

    },
    mounted() {
        var self = this;
        try {
            //네이버로 로그인할때만 실행
            if (this.$route.query.code.length !== undefined) {
                var callbackFuc = async () => {

                    const res = await fetch('https://locolhost/api/sns_login_naver', {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify({
                            code: `${self.$route.query.code}`,
                            state: `${self.$route.query.state}`,

                        }),
                    })
                    const data = await res.json();
                    console.log(`네이버 로그인 : ${data.email}`)

                    //네이버 로그인 인증 코드 (nodejs api)

                }
                callbackFuc();
            }
        } catch (e) {
            console.log(e)
        }
    }
}
</script>
