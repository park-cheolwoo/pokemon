document.addEventListener('DOMContentLoaded', function () {
  // 각 버튼과 questlist 요소를 가져옵니다.
  var uplist1Img = document.getElementById("uplist1").getElementsByTagName("img")[0];
  var uplist2Img = document.getElementById("uplist2").getElementsByTagName("img")[0];
  var uplist3Img = document.getElementById("uplist3").getElementsByTagName("img")[0];

  var questlist1 = document.getElementById("questlist1");
  var questlist2 = document.getElementById("questlist2");
  var questlist3 = document.getElementById("questlist3");

  // 각 버튼을 클릭했을 때 questlist를 변경하고 버튼 이미지를 업데이트하는 함수
  function toggleQuestLists(listNumber) {
    if (listNumber === 1) {
      questlist1.style.display = "block";
      questlist2.style.display = "none";
      questlist3.style.display = "none";

      // 이미지 변경
      uplist1Img.src = "../images/play/quest/uplist1_1.png";  // uplist1의 이미지를 변경
      uplist2Img.src = "../images/play/quest/uplist2.png";  // uplist2의 이미지를 기본 이미지로 변경
      uplist3Img.src = "../images/play/quest/uplist3.png";  // uplist3의 이미지를 기본 이미지로 변경
    } else if (listNumber === 2) {
      questlist1.style.display = "none";
      questlist2.style.display = "block";
      questlist3.style.display = "none";

      // 이미지 변경
      uplist1Img.src = "../images/play/quest/uplist1.png"; // uplist1의 이미지를 변경
      uplist2Img.src = "../images/play/quest/uplist2_2.png"; // uplist2의 이미지를 변경
      uplist3Img.src = "../images/play/quest/uplist3.png";   // uplist3의 이미지를 기본 이미지로 변경
    } else if (listNumber === 3) {
      questlist1.style.display = "none";
      questlist2.style.display = "none";
      questlist3.style.display = "block";

      // 이미지 변경
      uplist1Img.src = "../images/play/quest/uplist1.png"; // uplist1의 이미지를 기본 이미지로 변경
      uplist2Img.src = "../images/play/quest/uplist2.png";   // uplist2의 이미지를 기본 이미지로 변경
      uplist3Img.src = "../images/play/quest/uplist3_3.png"; // uplist3의 이미지를 변경
    }
  }

  // 버튼 클릭 이벤트 추가
  document.getElementById("uplist1").addEventListener("click", function () {
    toggleQuestLists(1);
  });

  document.getElementById("uplist2").addEventListener("click", function () {
    toggleQuestLists(2);
  });

  document.getElementById("uplist3").addEventListener("click", function () {
    toggleQuestLists(3);
  });
});