const express = require('express');
const router = express.Router();

module.exports = router;

const passwords = new Map([["user", FNVHash("password")]]);

function FNVHash(string) {
  const PRIME = 0x01000193;
  let hval = 0x811c9dc5;

  for (let i = 0; i < string.length; i++) {
    hval *= PRIME;
    hval ^= string[i] & 0xFF;
  }

  return hval;
}

function authorize(login, password) {
  if (!login || !password || !passwords.has(login)) {
    return 400;
  }

  if (passwords.get(login) !== FNVHash(password)) {
    return 401;
  }

  return 200;
}

router.post('/', function (request, result, _) {
  const body = request.body;
  console.log(body);
  result.sendStatus(authorize(body.login, body.password));
});
