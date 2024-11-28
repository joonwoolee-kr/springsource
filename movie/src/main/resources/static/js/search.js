document.querySelector("[name='keyword']").addEventListener("keyup", (e) => {
  // 검색어 입력 확인
  if (e.keyCode == 13) {
    const keyword = e.target.value;
    if (keyword == "") {
      // 없으면 메세지 띄우고 돌려보내기
      alert("영화명을 입력해주세요.");
      return;
    }
    // 있으면 searchForm 찾아서 keyword 입력값 변경
    const searchForm = document.querySelector("#searchForm");
    searchForm.querySelector("[name='keyword']").value = keyword;
    // form submit
    searchForm.submit();
  }
});
