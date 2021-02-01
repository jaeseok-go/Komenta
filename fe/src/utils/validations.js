export function validateEmail(email) {
  var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  return re.test(String(email).toLowerCase());
}

export function validatePassword(value) {
  return value.length >= 8;
}

export function validatePhoneNum(number) {
  var num = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;
  return num.test(String(number));
}