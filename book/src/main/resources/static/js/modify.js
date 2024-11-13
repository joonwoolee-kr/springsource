// 삭제 클릭 시 actionForm 전송
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  // 정말로 삭제하시겠습니까? 알림창
  if (confirm("정말로 삭제하시겠습니까?")) {
    actionForm.action = "/book/delete";
    actionForm.submit();
  }
});

document.querySelector(".btn-secondary").addEventListener("click", (e) => {
  // 목록 클릭 시 a 태그 기능 중지
  e.preventDefault();
  // actionForm에서 id 요소 제거
  // actionForm.removeAttribute("id");
  actionForm.querySelector("[name='id']").remove();
  // actionForm method를 get으로 변경
  // actionForm.setAttribute("method", "get");
  actionForm.method = "get";
  // actionForm action은 list로 변경
  // actionForm.setAttribute("action", "list");
  actionForm.action = "list";
  // actionForm submit
  actionForm.submit();
});
