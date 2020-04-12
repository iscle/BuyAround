var mongoose = require('mongoose');

var Direction = require('./Direction').Schema;
var Category = require('./Category').Schema;

var StoreSchema = new mongoose.Schema({  
  name: String,
  direction: Direction,
  rating: Number,
  category: Category,
  since: Number,
  products: { type: [mongoose.Types.Schema.ObjectId], ref: 'Product' },
});
mongoose.model('Store', StoreSchema);

module.exports = mongoose.model('Store');