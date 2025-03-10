$(function () {

       // 우측 상단 버튼 클릭 // 

       $(document).on("click", ".item_category_healing", function () {
        $(".item_category_healing_text").toggleClass("cat_on");
         if ($(".item_category_healing_text").hasClass("cat_on")) {
           $(".item_category_healing_box").attr("src", "/images/green-bar.png");
         } else { $(".item_category_healing_box").attr("src", "/images/yellow-bar.png"); }
       });

       $(document).on("click", ".item_category_battle", function () {
         $(".item_category_battle_text").toggleClass("cat_on");
         if ($(".item_category_battle_text").hasClass("cat_on")) {
           $(".item_category_battle_box").attr("src", "/images/green-bar.png");
         } else {$(".item_category_battle_box").attr("src", "/images/yellow-bar.png");}
       });

       $(document).on("click", ".item_category_monsterball", function () {
         $(".item_category_monsterball_text").toggleClass("cat_on");
         if ($(".item_category_monsterball_text").hasClass("cat_on")) {
           $(".item_category_monsterball_box").attr("src", "/images/green-bar.png");
         } else {$(".item_category_monsterball_box").attr("src", "/images/yellow-bar.png");}
       });

       

       // 우측 상단 버튼 클릭 // 

       // 우측 테이블 아이템 선택 //
       $(document).on("click", ".item_list_img", function () {
         //let id = ${temp.id};
         $(".item_table").find(".item_list_img").removeClass("selected");
         let img = $(this).attr("src");
         $(this).toggleClass("selected");
         $(".item_describe_img").attr("src", img);
         $(".item_name").text("몬스터볼");
       });

       // 우측 테이블 아이템 선택 //

       // 왼쪽 아이템 설명창 //		

       $(document).on("click", ".item_dscrb_btn, .item_describe_exit", function () {
         $(".item_describe_container").toggle();
       })
       
       // 왼쪽 아이템 설명창 //

       // 왼쪽 구매버튼 모달창 // 
       $(document).on("click", ".item_buy_btn", function () {
         $(".item_total_count").val("1");
         $(".item_total_cost").text("20");
         $(".item_buy_container").toggle();
       });


       $(document).on("click", ".item_buy_no", function () {
         $(".item_buy_container").toggle();
       });


       $(document).on("click", ".upper_yes", function () {
         $(".item_total_count").val(Number($(".item_total_count").val()) + 1);
         $(".item_total_cost").text(Number($(".item_total_cost").text()) + 20);
         countChk();
       });

       $(document).on("click", ".lower_yes", function () {
         $(".item_total_count").val(Number($(".item_total_count").val())-1);
         $(".item_total_cost").text(Number($(".item_total_cost").text())-20);
         countChk();
       });

       $(document).on("input",".item_total_count", function () {
         let count = Number($(".item_total_count").val());
         let cost = Number(count*20);
         $(".item_total_count").val(count);
         $(".item_total_cost").text(cost);
         countChk();
       });

       const countChk = () => {
         let count = Number($(".item_total_count").val());
         if (count >= 99) {
           $(".item_upper").attr("src", "/images/store/gray-up.png");
           $(".item_upper").removeClass("upper_yes");
           return false;
         }
         else if (count <= 0) {
           $(".item_lower").attr("src", "/images/store/gray-down.png");
           $(".item_lower").removeClass("lower_yes");
           $(".item_buy_yes").attr("src", "/images/store/gray-check.png");
           $(".item_buy_yes").removeClass("buy_ok");
           $(".item_cost_warning").toggle();
           return false;
         } else {
           $(".item_cost_warning").toggle();
           $(".item_upper").attr("src", "/images/store/yellow-up.png");
           $(".item_upper").addClass("upper_yes");
           $(".item_lower").attr("src", "/images/store/yellow-down.png");
           $(".item_lower").addClass("lower_yes");
           $(".item_buy_yes").attr("src", "/images/store/green-check.png");
           $(".item_buy_yes").addClass("buy_ok");
         }
       }

         $(document).on("click", ".buy_ok", function () {
           let name = $(".item_dscrb_name").text();
           let count = Number($(".item_total_count").val());
           let cost = Number($(".item_total_cost").text());
           $(".item_buy_container").toggle();
           $(".item_confirm_name").text(name);
           $(".item_confirm_count").text(count + " 개를");
           $(".item_confirm_cost").text(cost + " 에");
           $(".item_confirm_container").toggle();
         });

         $(document).on("click", ".item_confirm_no", function () {
           $(".item_confirm_container").toggle();
         });
         
         
         $(document).on("click", ".item_next_page, .item_prev_page", function () {
           $(".item_dscrb, .item_list, .item_lotto_container").toggle();
         });
         
		 $(document).on("click",".lotto_info_btn, .lotto_info_exit_btn",function(){
			$(".item_lotto_info_container").toggle();
		 });

         
       });   