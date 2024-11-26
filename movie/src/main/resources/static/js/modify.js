// modifyForm 찾은 후 action = "/movie/remove"
const modifyForm = document.querySelector("#modifyForm");
if (modifyForm) {
  modifyForm.addEventListener("submit", (e) => {
    e.preventDefault();
    if (!confirm("정말로 삭제하시겠습니까?")) {
      return;
    }
    modifyForm.action = "/movie/remove";
    modifyForm.submit();
  });
}
