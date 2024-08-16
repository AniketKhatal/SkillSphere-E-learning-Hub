export const validEmail = new RegExp(
  "^[a-zA-Z0-9._:$!%-]+@[a-zA-Z0-9.-]+.[a-zA-Z]$"
);
export const validPassword = new RegExp("^[0-9]{1,6}$");

export const onlyChar = new RegExp("^[A-Za-z]+$");

//changed regex
//export const onlyPhone = new RegExp("^[0-9]{10}$");
export const onlyPhone = new RegExp("^[1-9][0-9]{9}$");
