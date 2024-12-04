// 전체 리뷰 보여줄 영역 가져오기
const reviewList = document.querySelector(".review-list");
// 리뷰폼 영역 가져오기
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

// 영화의 전체 리뷰 가져오기
const reviewLoaded = () => {
  fetch(`/reviews/${mno}/all`)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      // 도착한 data 화면에 보여주기

      document.querySelector(".review-cnt").innerHTML = data.length;

      if (data.length > 0) {
        reviewList.classList.remove("hidden");
      }

      let result = "";
      data.forEach((review) => {
        result += `<div class="d-flex justify-content-between my-2 border-bottom py-2 review-row" data-rno="${review.reviewNo}">`;
        result += `<div class="flex-grow-1 align-self-center">`;
        result += `<div><span class="font-semibold">${review.text}</span></div>`;
        result += `<div class="small text-muted">`;
        result += `<span class="d-inline-block mr-3">${review.nickname}</span>`;
        result += `평점 : `;
        //result += `<span class="grade">${review.grade}</span><div class="starrr"></div></div>`;
        result += `<span class="grade">${review.grade}</span><div class="starrr"></div></div>`;
        result += `<div class="text-muted"><span class="small">${formatDate(
          review.regDate
        )}</span></div></div>`;
        // 리뷰 작성자 == 로그인 사용자
        if (review.email == loginUser) {
          result += `<div class="d-flex flex-column align-self-center">`;
          result += `<div class="mb-2">`;
          result += `<button class="btn btn-outline-danger btn-sm">삭제</button>`;
          result += `</div>`;
          result += `<div class="mb-2">`;
          result += `<button class="btn btn-outline-success btn-sm">수정</button>`;
          result += `</div></div>`;
        }
        result += `</div>`;
      });

      // 리뷰 영역에 보여주기
      reviewList.innerHTML = result;
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
  const mid = reviewForm.mid.value;

  const review = {
    reviewNo: reviewNo,
    text: text,
    grade: grade || 0,
    mno: mno,
    mid: mid,
    email: email,
    nickname: nickname,
  };

  if (!reviewNo) {
    // 신규작성
    fetch(`/reviews/${mno}`, {
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfValue,
      },
      method: "post",
      body: JSON.stringify(review),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);

        if (data) {
          alert(data + " 리뷰를 작성했습니다.");

          reviewForm.reviewNo.value = "";
          reviewForm.text.value = "";
          reviewForm
            .querySelector(".starrr a:nth-child(" + grade + ")")
            .click();
          reviewLoaded();
        }
      });
  } else {
    // 수정
    fetch(`/reviews/${mno}/${reviewNo}`, {
      headers: {
        "content-type": "application/json",
        "X-CSRF-TOKEN": csrfValue,
      },
      method: "put",
      body: JSON.stringify(review),
    })
      .then((response) => response.text())
      .then((data) => {
        console.log(data);

        if (data) {
          alert(data + " 리뷰가 수정되었습니다.");

          reviewForm.reviewNo.value = "";
          reviewForm.text.value = "";
          reviewForm
            .querySelector(".starrr a:nth-child(" + grade + ")")
            .click();
          reviewForm.querySelector(".btn-outline-danger").innerHTML =
            "리뷰 등록";

          reviewLoaded();
        }
      });
  }
});

// 리뷰 삭제 및 조회
reviewList.addEventListener("click", (e) => {
  // 어느 삭제 버튼에서 발생한 이벤트인가?
  const btn = e.target;
  // reviewNo 가져오기(data-rno 값)
  const reviewNo = btn.closest(".review-row").dataset.rno;
  // 작성자 email
  const email = reviewForm.email.value;
  const form = new FormData();
  form.append("email", email);

  if (btn.classList.contains("btn-outline-danger")) {
    if (!confirm("리뷰를 삭제하시겠습니까?")) return;

    fetch(`/reviews/${mno}/${reviewNo}`, {
      headers: {
        "X-CSRF-TOKEN": csrfValue,
      },
      method: "delete",
      body: form,
    })
      .then((response) => response.text())
      .then((data) => {
        if (data) {
          alert(data + " 리뷰가 삭제되었습니다.");
          reviewLoaded();
        }
      });
  } else {
    // 수정할 review 화면에 보여주기

    fetch(`/reviews/${mno}/${reviewNo}`)
      .then((response) => response.json())
      .then((data) => {
        console.log(data);

        // 도착한 데이터 reviewForm 안에 보여주기
        // reviewForm.querySelector("reviewNo").value
        reviewForm.reviewNo.value = `${data.reviewNo}`;
        reviewForm.email.value = `${data.email}`;
        reviewForm.nickname.value = `${data.nickname}`;
        reviewForm.text.value = `${data.text}`;

        reviewForm
          .querySelector(".starrr a:nth-child(" + data.grade + ")")
          .click();

        reviewForm.querySelector(".btn-outline-danger").innerHTML = "리뷰 수정";
      });
  }
});

//이미지 모달 요소 가져오기
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
    ).innerHTML = `<img src="/upload/display?fileName=${file}&size=1" alt="" style="width:100%">`;
    e.stopImmediatePropagation();
  });
}
