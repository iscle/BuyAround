var mongoose = require('mongoose');

var StoreCategorySchema = new mongoose.Schema({  
  name: String,
});
mongoose.model('StoreCategory', StoreCategorySchema);

module.exports = mongoose.model('StoreCategory');