var mongoose = require('mongoose');

var ProductCategorySchema = new mongoose.Schema({  
  name: String,
});
mongoose.model('ProductCategory', ProductCategorySchema);

module.exports = mongoose.model('ProductCategory');