<template>
<div>
    <h1>Test</h1>
    Test
    <div>
        <div id="naver_id_login"></div>
    </div>
</div>
</template>

<script>
export default {
    name: 'HelloWorld',
    mounted() {

      var naver_id_login = new naver_id_login("z_xevkfqoAuqghG2b8CF", "http://localhost:8081/Test");
      // 접근 토큰 값 출력
      console.log(naver_id_login.oauthParams.access_token);
      // 네이버 사용자 프로필 조회
      naver_id_login.get_naver_userprofile(naverSignInCallback());
      // 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
      function naverSignInCallback() {
        console.log(naver_id_login.getProfileData('email'));
        console.log(naver_id_login.getProfileData('name'));
        console.log(naver_id_login.getProfileData('profileImg'));
        console.log(naver_id_login.getProfileData('gender'));
        console.log(naver_id_login.getProfileData('bityhday'));
      }

      new window.naver_id_login("z_xevkfqoAuqghG2b8CF", "http://localhost:8081/Test");
    console.log("access token", naver_id_login.getAccessToken()); // 정상적 로그인이 된 경우 access token값 출력

    }
}
</script>
