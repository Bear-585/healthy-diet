/*!40000 DROP DATABASE IF EXISTS `healthy_diet`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `healthy_diet` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `healthy_diet`;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `admin_id` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COLLATE utf8mb4_unicode_ci COMMENT '提问',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复',
  `is_reply` int(11) DEFAULT NULL COMMENT '是否回复',
  `is_read` int(11) DEFAULT '0' COMMENT '已读/未读(1:已读,0:未读)',
  `uname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `avatar` longtext COLLATE utf8mb4_unicode_ci COMMENT '用户头像',
  `type` int(11) DEFAULT '1' COMMENT '内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='在线客服';

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2026-02-13 11:38:10',1,1,'提问1','回复1',1,1,'用户名1','upload/chat_avatar1.jpg,upload/chat_avatar2.jpg,upload/chat_avatar3.jpg',1),(2,'2026-02-13 11:38:10',2,2,'提问2','回复2',2,2,'用户名2','upload/chat_avatar2.jpg,upload/chat_avatar3.jpg,upload/chat_avatar4.jpg',2),(3,'2026-02-13 11:38:10',3,3,'提问3','回复3',3,3,'用户名3','upload/chat_avatar3.jpg,upload/chat_avatar4.jpg,upload/chat_avatar5.jpg',3),(4,'2026-02-13 11:38:10',4,4,'提问4','回复4',4,4,'用户名4','upload/chat_avatar4.jpg,upload/chat_avatar5.jpg,upload/chat_avatar6.jpg',4),(5,'2026-02-13 11:38:10',5,5,'提问5','回复5',5,5,'用户名5','upload/chat_avatar5.jpg,upload/chat_avatar6.jpg,upload/chat_avatar7.jpg',5),(6,'2026-02-13 11:38:11',6,6,'提问6','回复6',6,6,'用户名6','upload/chat_avatar6.jpg,upload/chat_avatar7.jpg,upload/chat_avatar8.jpg',6),(7,'2026-02-13 11:38:11',7,7,'提问7','回复7',7,7,'用户名7','upload/chat_avatar7.jpg,upload/chat_avatar8.jpg,upload/chat_avatar1.jpg',7),(8,'2026-02-13 11:38:11',8,8,'提问8','回复8',8,8,'用户名8','upload/chat_avatar8.jpg,upload/chat_avatar1.jpg,upload/chat_avatar2.jpg',8),(9,'2026-02-13 11:52:16',13,13,NULL,'您好，有什么可以帮您？',0,0,NULL,NULL,1),(10,'2026-02-13 11:52:38',13,NULL,'upload/1770983558304_703.jpg',NULL,0,0,'002','upload/consumer_profile3.jpg',2),(11,'2026-02-13 11:52:54',13,NULL,'分析上图菜品数据',NULL,0,0,'002','upload/consumer_profile3.jpg',1),(12,'2026-02-13 11:54:12',13,13,NULL,'您好，有什么可以帮您？',0,0,NULL,NULL,1),(13,'2026-02-13 11:54:22',13,NULL,'upload/1770983662360_846.jpg',NULL,0,0,'002','upload/consumer_profile3.jpg',2),(14,'2026-02-13 11:54:33',13,NULL,'分析上图菜品数据',NULL,0,0,'002','upload/consumer_profile3.jpg',1),(15,'2026-02-13 11:55:24',13,13,NULL,'由于你没有提供具体的“上图菜品数据”内容，以下为你提供一个通用的菜品数据分析框架，你可以根据实际数据情况进行对应分析：\n\n### 菜品基本信息分析\n1. **菜品名称与类别**\n    - **统计不同类别菜品数量**：比如将菜品分为热菜、凉菜、汤品、主食等类别，统计每个类别下菜品的数量，了解餐厅菜品结构的丰富程度。例如，热菜有 20 种，凉菜 10 种，汤品 5 种，主食 8 种，可看出热菜在菜品结构中占比较大。\n    - **分析特色菜品**：找出具有独特风味、烹饪方法或食材的菜品，这些菜品往往是餐厅的招牌，能吸引顾客。比如某道采用当地稀有食材制作的特色热菜，可能是餐厅的亮点。\n2. **价格分布**\n    - **划分价格区间**：根据菜品价格将菜品划分为不同区间，如低价区（0 - 30 元）、中价区（31 - 60 元）、高价区（61 元及以上）。统计每个价格区间的菜品数量，分析价格分布是否合理。例如，低价区菜品较多，可能适合大众消费；若高价区菜品占比过高，需考虑目标客户群体的消费能力。\n    - **价格与菜品类型关联**：观察不同类型菜品（如热菜、凉菜）在各价格区间的分布情况。一般来说，热菜可能因烹饪复杂、食材成本高而在高价区占比较大；凉菜相对简单，价格可能集中在中低价区。\n\n### 菜品销售数据分析\n1. **销售量**\n    - **畅销菜品与滞销菜品**：统计一段时间内（如一个月）各菜品的销售数量，找出销售量排名前几的畅销菜品和排名靠后的滞销菜品。畅销菜品可能是顾客喜爱的热门选择，可考虑加大推广；滞销菜品则需分析原因，如口味不符合大众、价格过高或宣传不足等。\n    - **销售量趋势**：分析不同时间段（如周、月、季度）各菜品的销售量变化趋势。例如，某道汤品在冬季销售量明显上升，说明其具有季节性需求，可在相应季节加大供应和推广。\n2. **销售额**\n    - **销售额贡献**：计算各菜品的销售额，分析哪些菜品对餐厅整体销售额贡献较大。即使某些菜品销售量不是最高，但单价较高，也可能成为销售额的重要来源。例如，一道高价海鲜热菜，虽然销售数量有限，但销售额占比可能较大。\n    - **销售额与销售量关系**：对比销售额和销售量排名，找出销售量高但销售额低或销售额高但销售量低的菜品。对于前者，可能是价格过低导致利润空间有限；对于后者，可能是高附加值菜品，可进一步挖掘其市场潜力。\n\n### 菜品成本与利润分析\n1. **成本构成**\n    - **食材成本**：分析各菜品主要食材的成本占比，了解食材成本对菜品成本的影响。例如，一道以牛肉为主要食材的热菜，牛肉成本可能占菜品总成本的较大比例。\n    - **其他成本**：考虑菜品制作过程中的其他成本，如调料、燃料、人工等。不同菜品的制作工艺和复杂程度不同，这些成本也会有所差异。例如，一道需要长时间炖煮的菜品，燃料和人工成本可能相对较高。\n2. **利润情况**\n    - **毛利率计算**：根据菜品售价和成本计算各菜品的毛利率，毛利率 =（售价 - 成本）/ 售价 × 100%。分析不同菜品的毛利率水平，找出高利润和低利润菜品。高利润菜品可适当增加推广，低利润菜品则需考虑是否调整价格或优化成本。\n    - **利润贡献**：结合销售量和毛利率，计算各菜品对餐厅整体利润的贡献。有些菜品虽然毛利率不是最高，但销售量大，整体利润贡献可能较大；而有些高毛利率但销售量低的菜品，对利润的贡献可能有限。\n\n### 顾客反馈分析\n1. **好评与差评菜品**\n    - **收集顾客评价**：通过在线点评平台、餐厅反馈表等渠道收集顾客对菜品的评价，统计好评和差评数量。找出好评较多的菜品，分析其受欢迎的原因，如口味独特、食材新鲜等；对于差评较多的菜品，深入了解顾客不满意的具体方面，如味道不佳、分量不足等，及时进行改进。\n2. **顾客建议**\n    - **整理顾客建议**：对顾客提出的关于菜品的建议进行整理和分类，如增加新菜品、调整菜品口味、改进菜品摆盘等。根据顾客建议，结合餐厅实际情况，考虑是否对菜品进行优化或创新。\n\n### 综合分析与决策建议\n1. **菜品结构优化**\n    - **根据销售和利润情况**：保留畅销且利润高的菜品，适当增加其供应；对于滞销且利润低的菜品，考虑淘汰或进行改进；对于有潜力但目前销售不佳的菜品，可制定针对性的推广策略。\n    - **丰富菜品类别**：根据顾客需求和市场趋势，适当增加新的菜品类别或特色菜品，满足不同顾客的口味偏好。\n2. **价格调整**\n    - **基于成本和利润分析**：对于成本过高导致利润低的菜品，可考虑适当提高价格；对于价格过高影响销售量的菜品，可评估是否降低价格或优化成本结构。\n    - **参考市场价格**：了解同行业类似菜品的价格水平，确保餐厅菜品价格具有竞争力。\n3. **菜品推广与营销**\n    - **针对畅销菜品**：加大宣传力度，如通过餐厅海报、社交媒体等渠道进行推广，提高其知名度和销售量。\n    - **对于特色菜品**：打造品牌故事，突出其独特之处，吸引顾客尝试。例如，介绍某道特色菜品的传统制作工艺或文化背景。\n    - **开展促销活动**：针对滞销菜品或新菜品，推出促销活动，如打折、买一送一等，刺激顾客购买。',NULL,0,NULL,NULL,1),(16,'2026-02-13 11:56:07',16,16,NULL,'您好，有什么可以帮您？',0,0,NULL,NULL,1),(17,'2026-02-13 11:56:13',16,NULL,'upload/1770983773102_441.jpg',NULL,0,0,'003','upload/consumer_profile6.jpg',2),(18,'2026-02-13 11:56:26',16,NULL,'分析图片菜品营养数据',NULL,0,0,'003','upload/consumer_profile6.jpg',1),(19,'2026-02-13 11:57:04',16,16,NULL,'若需分析图片中菜品的营养数据，可借助图像识别技术结合营养数据库实现，以下是具体分析流程与示例：\n\n### **一、分析流程**\n1. **图像识别菜品**  \n   使用AI模型（如阿里开源的“万物识别-中文-通用领域”模型）识别图片中的菜品名称及主要成分。该模型针对中式菜品优化，支持中文标签输出（如“麻婆豆腐”“清蒸鲈鱼”），识别准确率较高。\n\n2. **成分拆解与数据匹配**  \n   根据识别结果，将菜品拆解为原料（如鸡胸肉、胡萝卜、橄榄油），并从权威营养数据库（如《中国食物成分表标准版第6版》）中匹配每种原料的营养数据，包括能量、蛋白质、脂肪、碳水化合物、维生素、矿物质等。\n\n3. **计算总营养值**  \n   根据菜品中各原料的用量，按比例计算总营养成分。例如：  \n   - **鸡胸肉100克**：能量118千卡，蛋白质2克，脂肪1.6克  \n   - **胡萝卜50克**：能量20千卡，蛋白质0.5克，脂肪0.1克  \n   - **橄榄油20克**：能量180千卡，脂肪20克  \n   **总计**：能量318千卡，蛋白质2.5克，脂肪21.7克  \n\n4. **对比推荐摄入量**  \n   将计算结果与《中国居民膳食指南》的每日推荐摄入量对比，评估菜品是否符合健康标准。例如：  \n   - 成人每日推荐脂肪摄入量为60克，若该菜品脂肪占比过高（如占全天推荐量的36%），需调整原料或烹饪方式。\n\n### **二、技术实现工具**\n1. **图像识别模型**  \n   - **推荐模型**：阿里“万物识别-中文-通用领域”模型（轻量级、支持中文标签、中式菜品覆盖广）。  \n   - **部署方式**：通过PyTorch框架加载模型，输入菜品图片后输出识别结果（如“番茄炒蛋”）。\n\n2. **营养计算工具**  \n   - **专业软件**：使用FineBI等商业智能工具，可快速导入营养数据、生成可视化图表（如饼图展示营养素比例、柱状图对比不同菜品能量）。  \n   - **开源方案**：结合Python库（如Pandas、Matplotlib）编写脚本，实现数据计算与图表生成。\n\n### **三、示例分析**\n**菜品图片**：一张“番茄炒蛋”的照片  \n**分析结果**：  \n1. **识别结果**：番茄200克、鸡蛋150克、食用油10克。  \n2. **营养计算**：  \n   - **能量**：番茄32千卡 + 鸡蛋225千卡 + 油90千卡 = **347千卡**  \n   - **蛋白质**：番茄0.8克 + 鸡蛋19.5克 = **20.3克**  \n   - **脂肪**：番茄0.2克 + 鸡蛋15克 + 油10克 = **25.2克**  \n3. **健康评估**：  \n   - 能量占比：347千卡占成人每日推荐量（2000千卡）的17%，合理。  \n   - 脂肪占比：25.2克占推荐量（60克）的42%，偏高，建议减少用油量。\n\n### **四、优化建议**\n1. **调整原料比例**：减少高脂肪原料（如油、肉类），增加蔬菜占比。  \n2. **优化烹饪方式**：采用蒸、煮、炖代替煎、炸，降低脂肪摄入。  \n3. **定期更新数据**：根据季节变化或食材供应调整菜单，确保营养均衡。',0,0,NULL,NULL,1),(20,'2026-02-13 11:59:15',16,16,NULL,'您好，有什么可以帮您？',NULL,0,NULL,NULL,1);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` int(11) DEFAULT NULL COMMENT '参数类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='配置文件';

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL,1),(2,'picture2','upload/picture2.jpg',NULL,1),(3,'picture3','upload/picture3.jpg',NULL,1),(11,'baidu','{\"appId\":\"49214550\",\"apiKey\":\"7Otjpv2kn0ljQk45qXOXh5MO\",\"secretKey\":\"BMfbXRbTIVaB4C3SbRTtGqDv1wHDvyXS\"}',NULL,2),(12,'qianfan','{\"accessKey\":\"9dd9b2ea56b042c6a468bfeada1209a3\",\"accessSecretKey\":\"45a0aa98b86b4c54b9dc8ede3cc67274\"}',NULL,2),(21,'bLoginBackgroundImg','',NULL,3),(22,'bRegisterBackgroundImg','',NULL,3),(23,'bIndexBackgroundImg','',NULL,3),(24,'bTopLogo','',NULL,3),(25,'bHomeLogo','',NULL,3),(29,'appLoginBackgroundImg','',NULL,3),(30,'appRegisterBackgroudImg','',NULL,3),(31,'appLoginLogo','',NULL,3),(32,'appRegLogo','',NULL,3),(33,'wx','{\"appid\":\"\",\"secret\":\"\"}',NULL,2);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_recipe_comment`
--

DROP TABLE IF EXISTS `food_recipe_comment`;
CREATE TABLE `food_recipe_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ref_id` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatar` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbs_up_num` int(11) DEFAULT '0' COMMENT '赞',
  `crazily_num` int(11) DEFAULT '0' COMMENT '踩',
  `is_top` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `t_user_ids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `c_user_ids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='美食食谱评论';

--
-- Dumping data for table `food_recipe_comment`
--

LOCK TABLES `food_recipe_comment` WRITE;
/*!40000 ALTER TABLE `food_recipe_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `food_recipe_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `forum`
--

DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '帖子标题',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '帖子内容',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `username` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `avatar` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `is_done` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态',
  `is_top` int(11) DEFAULT '0' COMMENT '是否置顶',
  `top_time` datetime DEFAULT NULL COMMENT '置顶时间',
  `cover` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `is_anon` int(11) DEFAULT '0' COMMENT '是否匿名(1:是,0:否)',
  `del_flag` int(11) DEFAULT '0' COMMENT '是否删除(1:是,0:否)',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='交流论坛';

--
-- Dumping data for table `forum`
--

LOCK TABLES `forum` WRITE;
/*!40000 ALTER TABLE `forum` DISABLE KEYS */;
INSERT INTO `forum` VALUES (1,'2026-02-13 11:38:10','减脂期晚餐怎么吃才不饿','晚餐吃太少半夜会饿醒，吃多又怕胖，减脂期晚餐应该怎么搭配？有没有适合晚上吃的低卡食物？',0,'005','upload/forum_avatar1.jpg,upload/forum_avatar2.jpg,upload/forum_avatar3.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover1.jpg,upload/forum_cover2.jpg,upload/forum_cover3.jpg',1,0,1),(2,'2026-02-13 11:38:10','增肌期一天吃多少蛋白质合适','体重70kg，想增肌，每天蛋白质要吃到多少克？分几次吃比较好？有没有必要每顿都吃到饱和？',0,'008','upload/forum_avatar2.jpg,upload/forum_avatar3.jpg,upload/forum_avatar4.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover2.jpg,upload/forum_cover3.jpg,upload/forum_cover4.jpg',2,0,2),(3,'2026-02-13 11:38:10','外食怎么点才不影响减脂','经常加班只能吃外卖，有没有适合减脂的外卖类型？比如轻食、麻辣烫、盖浇饭怎么点才更健康？',0,'002','upload/forum_avatar3.jpg,upload/forum_avatar4.jpg,upload/forum_avatar5.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover3.jpg,upload/forum_cover4.jpg,upload/forum_cover5.jpg',3,0,3),(4,'2026-02-13 11:38:10','增肌期吃什么碳水最好','增肌期主食除了米饭和面条，还有哪些优质碳水？红薯、玉米、燕麦怎么安排到一日三餐里？',0,'006','upload/forum_avatar4.jpg,upload/forum_avatar5.jpg,upload/forum_avatar6.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover4.jpg,upload/forum_cover5.jpg,upload/forum_cover6.jpg',4,0,4),(5,'2026-02-13 11:38:10','女生减脂要不要做力量训练','女生只想瘦不想练出肌肉，是不是只做有氧就行？力量训练会不会让腿变粗？饮食上需要注意什么？',0,'007','upload/forum_avatar5.jpg,upload/forum_avatar6.jpg,upload/forum_avatar7.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover5.jpg,upload/forum_cover6.jpg,upload/forum_cover7.jpg',5,0,5),(6,'2026-02-13 11:38:10','减脂期早餐吃什么最顶饱','减脂期早上总是很饿，早餐吃什么既能控制热量又能扛饿？鸡蛋、燕麦、全麦面包怎么搭配比较好？',0,'003','upload/forum_avatar6.jpg,upload/forum_avatar7.jpg,upload/forum_avatar8.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover6.jpg,upload/forum_cover7.jpg,upload/forum_cover8.jpg',6,0,6),(7,'2026-02-13 11:38:10','备赛期怎么控制体脂又不掉肌肉','备赛期体脂降得很慢，热量缺口已经很大了，是不是要增加有氧？蛋白质要不要提高？有没有避免掉肌肉的方法？',0,'004','upload/forum_avatar7.jpg,upload/forum_avatar8.jpg,upload/forum_avatar1.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover7.jpg,upload/forum_cover8.jpg,upload/forum_cover1.jpg',7,0,7),(8,'2026-02-13 11:38:10','健身期间能不能喝饮料','平时爱喝可乐和果汁，健身后是不是都要戒掉？无糖饮料能不能喝？喝黑咖啡对健身有帮助吗？',0,'001','upload/forum_avatar8.jpg,upload/forum_avatar1.jpg,upload/forum_avatar2.jpg','开放',0,'2026-02-13 19:38:10','upload/forum_cover8.jpg,upload/forum_cover1.jpg,upload/forum_cover2.jpg',8,0,8);
/*!40000 ALTER TABLE `forum` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_recipe`
--

DROP TABLE IF EXISTS `food_recipe`;
CREATE TABLE `food_recipe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `food_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '食品名称',
  `food_cover` longtext COLLATE utf8mb4_unicode_ci COMMENT '食品封面',
  `food_category` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '食品分类',
  `calories` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '热量',
  `protein` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '蛋白质',
  `fat` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '脂肪量',
  `nutritional_value` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营养价值',
  `required_ingredients` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '所需食材',
  `cooking_guide` longtext COLLATE utf8mb4_unicode_ci COMMENT '美食教学',
  `preparation_method` longtext COLLATE utf8mb4_unicode_ci COMMENT '制作方法',
  `dish_details` longtext COLLATE utf8mb4_unicode_ci COMMENT '菜品详情',
  `last_click_time` datetime DEFAULT NULL COMMENT '最近点击时间',
  `click_num` int(11) DEFAULT '0' COMMENT '点击次数',
  `discuss_num` int(11) DEFAULT '0' COMMENT '评论数',
  `store_up_num` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='美食食谱';

--
-- Dumping data for table `food_recipe`
--

LOCK TABLES `food_recipe` WRITE;
/*!40000 ALTER TABLE `food_recipe` DISABLE KEYS */;
INSERT INTO `food_recipe` VALUES (1,'2026-02-13 11:38:10','冬瓜虾仁汤','upload/food_recipe_冬瓜虾仁汤1.jpg,upload/food_recipe_冬瓜虾仁汤2.jpg,upload/food_recipe_冬瓜虾仁汤3.jpg','减脂餐','42大卡/100g','7.8g/100g','0.6g/100g','低热量、高水分，虾仁提供优质蛋白，冬瓜利尿消肿，减脂期佳品','冬瓜200g、虾仁100g、葱花5g、盐2g、香油2滴','','1.冬瓜去皮切薄片，虾仁去虾线洗净；2.锅中加清水烧开，放入冬瓜煮5分钟；3.加入虾仁煮至变色，加盐调味；4.撒葱花，滴香油即可出锅','清淡无油的减脂汤品，做法简单，饱腹又补水，适合晚餐食用','2026-02-13 19:38:10',1,0,1),(2,'2026-02-13 11:38:10','番茄牛腩煲','upload/food_recipe_番茄牛腩煲1.jpg,upload/food_recipe_番茄牛腩煲2.jpg,upload/food_recipe_番茄牛腩煲3.jpg','家常菜','168大卡/100g','13.2g/100g','9.7g/100g','牛腩富含血红素铁和蛋白质，番茄补充维生素C，促进铁吸收','牛腩200g、番茄2个、土豆1个、姜片5g、生抽10ml、盐2g','','1.牛腩切块焯水，番茄去皮切块，土豆切块；2.锅中热油炒香姜片，放牛腩翻炒至微黄；3.加番茄炒出汁，加水没过食材，大火烧开转小火炖1小时；4.加入土豆再炖20分钟，加盐和生抽调味','软烂入味的家常炖菜，汤汁浓郁，拌饭拌面都合适，老少皆宜','2026-02-13 19:38:10',2,0,2),(3,'2026-02-13 11:38:10','苦瓜炒鸡蛋','upload/food_recipe_苦瓜炒鸡蛋1.jpg,upload/food_recipe_苦瓜炒鸡蛋2.jpg,upload/food_recipe_苦瓜炒鸡蛋3.jpg','控糖餐','88大卡/100g','6.7g/100g','4.5g/100g','苦瓜含苦瓜皂苷，可辅助调节血糖，鸡蛋补充优质蛋白，低热量低糖','苦瓜1根、鸡蛋2个、蒜末5g、盐2g、食用油5ml','','1.苦瓜去瓤切薄片，加盐腌制10分钟后挤干水分；2.鸡蛋打散，加少许盐搅匀；3.热油下锅炒鸡蛋，成型后盛出；4.另起锅炒香蒜末，放苦瓜翻炒2分钟，倒入鸡蛋炒匀即可','适合糖友的家常快手菜，苦瓜去涩处理后口感清爽，不苦不腻','2026-02-13 19:38:10',3,0,3),(4,'2026-02-13 11:38:10','菠菜鸡胸肉卷','upload/food_recipe_菠菜鸡胸肉卷1.jpg,upload/food_recipe_菠菜鸡胸肉卷2.jpg,upload/food_recipe_菠菜鸡胸肉卷3.jpg','减脂餐','96大卡/100g','11.5g/100g','3.8g/100g','高蛋白、低脂肪、高纤维，生菜菠菜提供丰富维生素，减脂期代餐优选','鸡胸肉150g、菠菜100g、生菜叶4片、低脂沙拉酱10g','','1.鸡胸肉蒸熟撕成丝，菠菜焯水切段；2.生菜叶铺平，放适量鸡胸肉丝和菠菜段；3.挤上低脂沙拉酱，卷紧即可；4.可切段摆盘','无需开火的减脂快手餐，便携易带，适合上班或健身后食用','2026-02-13 19:38:10',4,0,4),(5,'2026-02-13 11:38:10','香煎三文鱼排','upload/food_recipe_香煎三文鱼排1.jpg,upload/food_recipe_香煎三文鱼排2.jpg,upload/food_recipe_香煎三文鱼排3.jpg','家常菜','208大卡/100g','22.3g/100g','12.1g/100g','富含Omega-3不饱和脂肪酸、维生素D，健脑益智，保护心血管','三文鱼排1块（150g）、柠檬1/2个、黑胡椒1g、盐1g、黄油5g','','1.三文鱼排洗净擦干，抹盐和黑胡椒腌制5分钟；2.平底锅放黄油融化，放入三文鱼排；3.中小火煎至两面金黄，约每面3分钟；4.挤上柠檬汁提味即可','西式家常做法，三文鱼煎制后外香里嫩，搭配柠檬解腻提鲜','2026-02-13 19:38:10',5,0,5),(6,'2026-02-13 11:38:10','红烧排骨','upload/food_recipe_红烧排骨1.jpg,upload/food_recipe_红烧排骨2.jpg,upload/food_recipe_红烧排骨3.jpg','家常菜','245大卡/100g','17.8g/100g','16.2g/100g','富含蛋白质、钙质和胶原蛋白，补充身体所需营养，增强体质','猪排骨300g、冰糖5g、姜片5g、八角1个、生抽15ml、老抽5ml、盐2g','','1.排骨切块焯水，捞出沥干；2.锅中放少许油，炒化冰糖，加排骨翻炒上色；3.放姜片八角炒香，加生抽老抽翻炒；4.加水没过排骨，大火烧开转小火炖40分钟，加盐收汁','色泽红亮、肉质软烂的家常菜，香味浓郁，是家庭聚餐的常见硬菜','2026-02-13 19:38:10',6,0,6),(7,'2026-02-13 11:38:10','紫薯燕麦粥','upload/food_recipe_紫薯燕麦粥1.jpg,upload/food_recipe_紫薯燕麦粥2.jpg,upload/food_recipe_紫薯燕麦粥3.jpg','减脂餐','112大卡/100g','3.6g/100g','2.1g/100g','低升糖、高纤维，燕麦饱腹感强，紫薯富含花青素，减脂期早餐优选','紫薯1个、燕麦片50g、牛奶200ml、红枣3颗','','1.紫薯去皮切小块，红枣去核；2.锅中加牛奶和清水，放入紫薯和红枣煮10分钟；3.加入燕麦片，煮5分钟至浓稠即可；4.可根据喜好加少许蜂蜜','软糯香甜的减脂早餐，暖胃又饱腹，做法简单，适合懒人操作','2026-02-13 19:38:10',7,0,7),(8,'2026-02-13 11:38:10','山药炒木耳','upload/food_recipe_山药炒木耳1.jpg,upload/food_recipe_山药炒木耳2.jpg,upload/food_recipe_山药炒木耳3.jpg','控糖餐','58大卡/100g','2.3g/100g','1.9g/100g','山药黏液蛋白可延缓糖分吸收，木耳膳食纤维丰富，适合糖友长期食用','山药200g、干木耳20g、胡萝卜50g、盐1g、葱花5g、食用油3ml','','1.山药去皮切片，木耳泡发焯水，胡萝卜切片；2.热油炒香葱花，放胡萝卜片翻炒1分钟；3.加入山药和木耳翻炒3分钟；4.加盐调味，炒匀出锅','清淡爽口的控糖小炒，食材温和易消化，适合中老年糖友日常食用','2026-02-13 19:38:10',8,0,8);
/*!40000 ALTER TABLE `food_recipe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news`
--

DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `introduction` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发布人',
  `avatar` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统公告';

--
-- Dumping data for table `news`
--

LOCK TABLES `news` WRITE;
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` VALUES (1,'2026-02-13 11:38:10','限时活动公告','介绍限时活动','upload/news_picture1.jpg','推出限时优惠活动，详情请查看活动内容','韩磊','upload/news_avatar1.jpg'),(2,'2026-02-13 11:38:10','支付系统优化通知','说明支付系统优化','upload/news_picture2.jpg','支付系统已优化，支付流程更便捷','杨丽','upload/news_avatar2.jpg'),(3,'2026-02-13 11:38:10','数据迁移通知','说明数据迁移相关事宜','upload/news_picture3.jpg','将于下周进行数据迁移，迁移期间部分功能可能受影响','王明','upload/news_avatar3.jpg'),(4,'2026-02-13 11:38:10','安全漏洞修复公告','说明修复漏洞情况','upload/news_picture4.jpg','已成功修复XX安全漏洞，保障系统安全','罗佳','upload/news_avatar4.jpg'),(5,'2026-02-13 11:38:10','新政策解读公告','解读新政策','upload/news_picture5.jpg','对最新发布的XX政策进行详细解读','胡斌','upload/news_avatar5.jpg'),(6,'2026-02-13 11:38:10','隐私政策变更通知','提示隐私政策变更','upload/news_picture6.jpg','隐私政策发生变更，请知悉','李芳','upload/news_avatar6.jpg'),(7,'2026-02-13 11:38:10','品牌战略调整公告','阐述品牌战略调整','upload/news_picture7.jpg','我司将对品牌战略进行调整，以适应市场变化','赵宇','upload/news_avatar7.jpg'),(8,'2026-02-13 11:38:10','系统故障修复通知','说明系统故障修复','upload/news_picture8.jpg','系统故障已修复，各项功能恢复正常','郭凯','upload/news_avatar8.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `popup_remind`
--

DROP TABLE IF EXISTS `popup_remind`;
CREATE TABLE `popup_remind` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '发布人id',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '角色',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '个人' COMMENT '类型',
  `brief` longtext COLLATE utf8mb4_unicode_ci COMMENT '简介',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `remind_time` datetime DEFAULT NULL COMMENT '提醒时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='弹窗提醒';

--
-- Dumping data for table `popup_remind`
--

LOCK TABLES `popup_remind` WRITE;
/*!40000 ALTER TABLE `popup_remind` DISABLE KEYS */;
INSERT INTO `popup_remind` VALUES (1,'2026-02-13 11:38:11',1,'角色1','标题1','个人','简介1','内容1','2026-02-13 19:38:11'),(2,'2026-02-13 11:38:11',2,'角色2','标题2','个人','简介2','内容2','2026-02-13 19:38:11'),(3,'2026-02-13 11:38:11',3,'角色3','标题3','个人','简介3','内容3','2026-02-13 19:38:11'),(4,'2026-02-13 11:38:11',4,'角色4','标题4','个人','简介4','内容4','2026-02-13 19:38:11'),(5,'2026-02-13 11:38:11',5,'角色5','标题5','个人','简介5','内容5','2026-02-13 19:38:11'),(6,'2026-02-13 11:38:11',6,'角色6','标题6','个人','简介6','内容6','2026-02-13 19:38:11'),(7,'2026-02-13 11:38:11',7,'角色7','标题7','个人','简介7','内容7','2026-02-13 19:38:11'),(8,'2026-02-13 11:38:11',8,'角色8','标题8','个人','简介8','内容8','2026-02-13 19:38:11');
/*!40000 ALTER TABLE `popup_remind` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_category`
--

DROP TABLE IF EXISTS `food_category`;
CREATE TABLE `food_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `food_category` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '食品分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `food_category` (`food_category`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='食品分类';

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES (1,'2026-02-13 11:38:10','减脂餐'),(2,'2026-02-13 11:38:10','家常菜'),(3,'2026-02-13 11:38:10','控糖餐'),(4,'2026-02-13 11:38:10','食品分类4'),(5,'2026-02-13 11:38:10','食品分类5'),(6,'2026-02-13 11:38:10','食品分类6'),(7,'2026-02-13 11:38:10','食品分类7'),(8,'2026-02-13 11:38:10','食品分类8');
/*!40000 ALTER TABLE `food_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_up`
--

DROP TABLE IF EXISTS `store_up`;
CREATE TABLE `store_up` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `ref_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `table_name` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `intel_type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';

--
-- Dumping data for table `store_up`
--

LOCK TABLES `store_up` WRITE;
/*!40000 ALTER TABLE `store_up` DISABLE KEYS */;
/*!40000 ALTER TABLE `store_up` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `table_name` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(255) NOT NULL COMMENT '密码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expirated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='token表';

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
INSERT INTO `token` VALUES (1,13,'002','consumer','用户','wowk48fwecm57el5eq46a1801t3kmsgq','2026-02-13 11:49:37','2026-02-13 12:52:13'),(2,1,'admin','users','管理员','8w9v9v6i2sefcjlerhyqw8up4k2a4kcu','2026-02-13 11:50:03','2026-02-13 12:50:04'),(3,16,'003','consumer','用户','qe9ovtm8u7n03kkxb72dscaf545dx65l','2026-02-13 11:56:02','2026-02-13 12:56:03');
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  `image` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-02-13 11:38:11','admin','admin','管理员','upload/image1.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diet_record`
--

DROP TABLE IF EXISTS `diet_record`;
CREATE TABLE `diet_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `meal_time` datetime DEFAULT NULL COMMENT '用餐时间',
  `food_name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '食物名称',
  `calories` int(11) DEFAULT NULL COMMENT '摄入热量/千卡',
  `protein` int(11) DEFAULT NULL COMMENT '摄入蛋白质/g',
  `fat_intake` double DEFAULT NULL COMMENT '摄入脂肪/g',
  `carbohydrate_intake` double DEFAULT NULL COMMENT '摄入碳水化合物/g',
  `dietary_fiber_intake` int(11) DEFAULT NULL COMMENT '摄入膳食纤维/g',
  `vitamin` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '维生素',
  `record_time` date DEFAULT NULL COMMENT '记录时间',
  `diet_analysis` longtext COLLATE utf8mb4_unicode_ci COMMENT '饮食分析',
  `account` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '账号',
  `full_name` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '姓名',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='饮食记录';

--
-- Dumping data for table `diet_record`
--

LOCK TABLES `diet_record` WRITE;
/*!40000 ALTER TABLE `diet_record` DISABLE KEYS */;
INSERT INTO `diet_record` VALUES (1,'2026-02-13 11:38:10','2026-02-13 19:38:10','鲫鱼',105,105,105,105,105,'维生素C、钾','2026-02-13','含优质蛋白质，钙元素，磷元素，铁元素','005','林书豪','upload/diet_record_痛风缓解期低嘌呤食谱1.jpg,upload/diet_record_痛风缓解期低嘌呤食谱2.jpg,upload/diet_record_痛风缓解期低嘌呤食谱3.jpg',1),(2,'2026-02-13 11:38:10','2026-02-13 19:38:10','胡萝卜',83,83,83,83,83,'维生素B6、烟酸','2026-02-13','含β-胡萝卜素，维生素A','008','徐婷','upload/diet_record_男性更年期调理饮食方案1.jpg,upload/diet_record_男性更年期调理饮食方案2.jpg,upload/diet_record_男性更年期调理饮食方案3.jpg',2),(3,'2026-02-13 11:38:10','2026-02-13 19:38:10','草莓',142,142,142,142,142,'维生素B2、钙','2026-02-13','含维生素C','002','陈浩','upload/diet_record_外卖爱好者健康点餐指南1.jpg,upload/diet_record_外卖爱好者健康点餐指南2.jpg,upload/diet_record_外卖爱好者健康点餐指南3.jpg',3),(4,'2026-02-13 11:38:10','2026-02-13 19:38:10','橙子',182,182,182,182,182,'维生素C、维生素B1','2026-02-13','富含维生素C','006','孙伟','upload/diet_record_孕妇中期营养食谱1.jpg,upload/diet_record_孕妇中期营养食谱2.jpg,upload/diet_record_孕妇中期营养食谱3.jpg',4),(5,'2026-02-13 11:38:10','2026-02-13 19:38:10','土豆',109,109,109,109,109,'维生素B族、硒','2026-02-13','含碳水化合物，钾元素，维生素C','007','郑晓琳','upload/diet_record_抑郁症患者营养调理食谱1.jpg,upload/diet_record_抑郁症患者营养调理食谱2.jpg,upload/diet_record_抑郁症患者营养调理食谱3.jpg',5),(6,'2026-02-13 11:38:10','2026-02-13 19:38:10','洋葱',85,85,85,85,85,'维生素C、维生素B6','2026-02-13','含硫化物，维生素C','003','李思远','upload/diet_record_成年人日常均衡饮食推荐1.jpg,upload/diet_record_成年人日常均衡饮食推荐2.jpg,upload/diet_record_成年人日常均衡饮食推荐3.jpg',6),(7,'2026-02-13 11:38:10','2026-02-13 19:38:10','红薯',98,98,98,98,98,'维生素C、维生素K','2026-02-13','含碳水化合物，膳食纤维，β-胡萝卜素，钾元素','004','朱琳','upload/diet_record_运动爱好者增肌饮食方案1.jpg,upload/diet_record_运动爱好者增肌饮食方案2.jpg,upload/diet_record_运动爱好者增肌饮食方案3.jpg',7),(8,'2026-02-13 11:38:10','2026-02-13 19:38:10','西兰花',148,148,148,148,148,'维生素D、维生素B12','2026-02-13','富含维生素C','001','杨洁','upload/diet_record_青少年长高营养方案1.jpg,upload/diet_record_青少年长高营养方案2.jpg,upload/diet_record_青少年长高营养方案3.jpg',8);
/*!40000 ALTER TABLE `diet_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `consumer`
--

DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `account` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `full_name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '姓名',
  `gender` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `phone` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机',
  `height` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '身高/m',
  `weight` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '体重/kg',
  `bmi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'BMI',
  `profile` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `openid` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信openid',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信昵称',
  `avatar` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '微信头像',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户';

--
-- Dumping data for table `consumer`
--

LOCK TABLES `consumer` WRITE;
/*!40000 ALTER TABLE `consumer` DISABLE KEYS */;
INSERT INTO `consumer` VALUES (11,'2026-02-13 11:38:10','005','123456','林书豪','男','15065432109','1.60','65','BMI1','upload/consumer_profile1.jpg','','',''),(12,'2026-02-13 11:38:10','008','123456','徐婷','男','13890123456','1.82','95','BMI2','upload/consumer_profile2.jpg','','',''),(13,'2026-02-13 11:38:10','002','123456','陈浩','女','13023456789','1.73','45','BMI3','upload/consumer_profile3.jpg','','',''),(14,'2026-02-13 11:38:10','006','123456','孙伟','男','15178901234','1.71','67','BMI4','upload/consumer_profile4.jpg','','',''),(15,'2026-02-13 11:38:10','007','123456','郑晓琳','男','13290123456','1.88','77','BMI5','upload/consumer_profile5.jpg','','',''),(16,'2026-02-13 11:38:10','003','123456','李思远','男','13812345678','1.76','82','BMI6','upload/consumer_profile6.jpg','','',''),(17,'2026-02-13 11:38:10','004','123456','朱琳','男','13678901234','1.69','45','15.76','upload/consumer_profile7.jpg','','',''),(18,'2026-02-13 11:38:10','001','123456','杨洁','男','15267890123','1.85','75','21.91','upload/consumer_profile8.jpg','','','');
/*!40000 ALTER TABLE `consumer` ENABLE KEYS */;
UNLOCK TABLES;
