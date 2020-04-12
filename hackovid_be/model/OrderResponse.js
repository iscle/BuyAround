class OrderResponse {
  constructor(status, orders) {
    this.status = status;
    this.orders = orders;
  }
}

module.exports = OrderResponse;