// 전체 리뷰 영역
const reviewList = document.querySelector(".review-list");
// 리뷰폼
const reviewForm = document.querySelector("#reviewForm");

// 날짜 포맷
const formatDate = (str) => {
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

// 영화 전체 리뷰 가져오기
const reviewLoaded = () => {
  fetch(`/reviews/${mno}/all`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);

      document.querySelector(".review-cnt").innerHTML = data.length;

      if (data.length > 0) {
        let result = "";
        data.forEach((review) => {
          result += `<div class="d-flex justify-content-between my-2 border-bottom py-2 review-row" data-rno="${review.reviewNo}">`;
          result += `<div class="flex-grow-1 align-self-center">`;
          result += `<span class="font-semibold">${review.text}</span>`;
          result += `<div class="small text-muted">`;
          result += `<span class="d-inline-block mr-3">${review.nickname}</span>`;
          result += `평점: `;
          result += `<span class="grade">${review.grade}</span><div class="starrr"></div>`;
          result += `</div><div class="text-muted">`;
          result += `<span class="small">${formatDate(review.regDate)}</span>`;
          result += `</div></div><div class="d-flex flex-column align-self-center">`;
          result += `<div class="mb-2">`;
          result += `<button class="btn btn-outline-danger btn-sm">삭제</button></div>`;
          result += `<div class="mb-2">`;
          result += `<button class="btn btn-outline-success btn-sm">수정</button></div></div></div>`;
        });
        reviewList.classList.remove("hidden");
        reviewList.innerHTML = result;
      }
    });
};
reviewLoaded();

// 리뷰 작성 및 수정
reviewForm.addEventListener("submit", (e) => {
  e.preventDefault();

  const reviewNo = reviewForm.reviewNo.value;
  const email = reviewForm.email.value;
  const nickname = reviewForm.nickname.value;
  const text = reviewForm.text.value;

  const review = {
    reviewNo: reviewNo,
    text: text,
    grade: grade,
    mno: mno,
    mid: 46,
    nickname: "nickname46",
    email: "user46@gmail.com",
  };

  if (!reviewNo) {
    // 신규 작성
    fetch(`/reviews/${mno}`, {
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(review),
      method: "post",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("리뷰 삽입 에러 발생");
        }
        return response.text();
      })
      .then((data) => {
        alert(data + " 리뷰가 작성되었습니다.");
        reviewForm.reviewNo.value = "";
        reviewForm.email.value = "";
        reviewForm.nickname.value = "";
        reviewForm.text.value = "";
        reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();
        reviewLoaded();
      });
  } else {
    // 수정
    fetch(`/reviews/${mno}/${reviewNo}`, {
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(review),
      method: "put",
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("리뷰 수정 에러 발생");
        }
        return response.text();
      })
      .then((data) => {
        alert(data + " 리뷰가 수정되었습니다.");
        reviewForm.reviewNo.value = "";
        reviewForm.email.value = "";
        reviewForm.nickname.value = "";
        reviewForm.text.value = "";
        reviewForm.querySelector(".starrr a:nth-child(" + grade + ")").click();
        reviewForm.querySelector(".btn-outline-danger").innerHTML = "리뷰 등록";
        reviewLoaded();
      });
  }
});

// 리뷰 삭제
reviewList.addEventListener("click", (e) => {
  // 실제 이벤트 발생 요소는 누구인가?
  console.log(e.target);
  const btn = e.target;

  // 이벤트가 발생한 버튼이 속한 data-reviewNo 를 가지고 있는 부모 태그 찾아오기
  // console.log(btn.closest(".review-row"));

  // data-rno 값을 가져오기
  // 수정 버튼 클릭시 reviewNo 가져오기
  const reviewNo = btn.closest(".review-row").dataset.rno;
  console.log(reviewNo);

  // 수정 or 삭제가 눌러졌는지 구분
  // 클래스명 : classList
  if (btn.classList.contains("btn-outline-danger")) {
    // 댓글 삭제
    if (!confirm("리뷰를 삭제하시겠습니까?")) {
      return;
    }
    fetch(`/reviews/${mno}/${reviewNo}`, {
      // headers: {
      //   "content-type": "application/json",
      //   "X-CSRF-TOKEN": csrfValue,
      // },
      method: "delete",
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);

        if (data) {
          alert(data + " 번 리뷰가 삭제되었습니다.");

          // 리뷰 영역 새로 가져오기
          reviewLoaded();
        }
      });
  } else if (btn.classList.contains("btn-outline-success")) {
    fetch(`/reviews/${mno}/${reviewNo}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        // 해당 댓글을 reviewForm 안에 보여주기
        reviewForm.reviewNo.value = `${data.reviewNo}`;
        reviewForm.email.value = `${data.email}`;
        reviewForm.nickname.value = `${data.nickname}`;
        reviewForm.text.value = `${data.text}`;

        reviewForm
          .querySelector(".starrr a:nth-child(" + data.grade + ")")
          .click();

        reviewForm.querySelector(".btn-outline-danger").innerHTML = "리뷰 수정";

        // reviewForm.reviewNo.value = data.nickname;
        // reviewForm.querySelector("[name='text']").innerHTML = data.text;
        // reviewForm.querySelector(".grade div").remove();
        // reviewForm
        //   .querySelector(".grade")
        //   .insertAdjacentHTML("beforeend", "<div class='starrr'></div>");
        // grade = data.grade;
        // $(".starrr").starrr({
        //   rating: grade,
        // });
      });
  }
});

// 이미지 모달 요소 가져오기
const imgModal = document.querySelector("#imgModal");

if (imgModal) {
  imgModal.addEventListener("show.bs.modal", (e) => {
    // 모달을 뜨게 만든 img 요소 가져오기
    const posterImg = e.relatedTarget;

    // data- 가져오기
    const file = posterImg.getAttribute("data-file");
    console.log(file);

    imgModal.querySelector(".modal-title").textContent = `${title}`;
    imgModal.querySelector(
      ".modal-body"
    ).innerHTML = `<img src="/upload/display?fileName=${file}" alt="">`;
  });
}
