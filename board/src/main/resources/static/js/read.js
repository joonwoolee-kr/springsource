// 날짜 처리 함수
const formantDateTime = (str) => {
  const date = new Date(str);

  return (
    date.getFullYear() +
    "/" +
    (date.getMonth() + 1) +
    "/" +
    date.getDate() +
    " " +
    date.getHours() +
    ":" +
    date.getMinutes()
  );
};

// 페이지가 로드되면 현재 bno의 댓글 가져오기
const replyLoaded = () => {
  fetch(`/replies/board/${bno}`)
    .then((response) => {
      if (!response.ok) throw new Error("에러");
      return response.json();
    })
    .then((data) => {
      console.log(data);

      // 전체 댓글 수 표기
      document.querySelector(".d-inline-block").innerHTML = data.length;

      // 전체 댓글 내용 표시
      const replyList = document.querySelector(".reply-list");
      let result = "";
      data.forEach((reply) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom reply-row" data-rno="">`;
        result += `<div class="p-3"><img src="" alt="" class="rounded-circle mx-auto d-block" /></div>`;
        result += `<div class="p-3"><img src="" alt="" class="rounded-circle mx-auto d-block" /></div>`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<span>${reply.replyer}</span>`;
        result += `<div><span class="fs-5">${reply.text}</span></div>`;
        result += `<div class="text-muted"><span class="small">`;
        result += `${formantDateTime(reply.regDate)}</span></div></div>`;
        result += `<div class="d-flex flex-column align-self-center">`;
        result += `<div class="mb-2"><button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
        result += `<div><button class="btn btn-outline-success btn-sm">수정</button></div></div></div>`;
      });
      replyList.innerHTML = result;
    });
};

replyLoaded();
