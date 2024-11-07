// 삭제 클릭 시 actionForm 전송
const actionForm = document.querySelector("#actionForm");
document.querySelector(".btn-danger").addEventListener("click", (e) => {
  // 정말로 삭제하시겠습니까? 알림창
  if (confirm("정말로 삭제하시겠습니까?")) {
    actionForm.action = "/book/delete";
    actionForm.submit();
  }
});
