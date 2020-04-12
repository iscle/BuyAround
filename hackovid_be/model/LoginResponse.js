class LoginResponse {
  constructor(status, token) {
    this.status = status;
    this.token = token;
  }
}

module.exports = LoginResponse;