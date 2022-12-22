function fn_submit() {
   var fn = document.frm;

   //유효성 체크
   if (fn.v_jumin.value == "") {   //입력창 custname부분이 null이면
      alert("주민번호가 입력되지 않았습니다!");
      fn.v_jumin.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.v_name.value == "") {
      alert("성명이 입력되지 않았습니다!");
      fn.v_name.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.m_no.value == "") {
      alert("후보번호가 선택되지 않았습니다!");
      fn.m_no.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.v_time.value == "") {
      alert("투표시간이 입력되지 않았습니다!");
      fn.v_time.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.v_area.value == "") {
      alert("투표장소가 입력되지 않았습니다!");
      fn.v_area.focus();   //포커싱
      return false;   //함수 끝
   }
   if (fn.v_confirm.value.value == "N") {
      alert("유권자확인이 선택되지 않았습니다!");
      fn.v_confirm.focus();   //포커싱
      return false;   //함수 끝
   } 
   fn.submit();   //
}

function restart() {
	alert("다시 작성해주세요.");
}