package com.baidu.utils;

import java.util.HashMap;
import java.util.Map;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.GsonUtils;
import com.baidu.ai.aip.utils.HttpUtil;



/**
 * 人脸检测与属性分析
 */
public class FaceDetect {

	public static String detect(byte[] imagesBytes) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			Map<String, Object> map = new HashMap<>();
			String image = Base64Util.encode(imagesBytes);
			map.put("image", image);
			map.put("image_type", "BASE64");
			//最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸；最大值10，检测图片中面积最大的几张人脸。
			map.put("max_face_num", "10");
			/*LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
			IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片
			WATERMARK表示带水印证件照：一般为带水印的小图，如公安网小图
			CERT表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
			默认LIVE*/
			map.put("face_type", "LIVE");
			String param = GsonUtils.toJson(map);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "24.b8cd3dae8ac564c7a3d4b4db79012818.2592000.1542784650.282335-14439847";
			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String detect(String image) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("image", image);
			map.put("image_type", "BASE64");
			//最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸；最大值10，检测图片中面积最大的几张人脸。
			map.put("max_face_num", "10");
			/*LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
			IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片
			WATERMARK表示带水印证件照：一般为带水印的小图，如公安网小图
			CERT表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
			默认LIVE*/
			map.put("face_type", "LIVE");
			String param = GsonUtils.toJson(map);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "24.b8cd3dae8ac564c7a3d4b4db79012818.2592000.1542784650.282335-14439847";
			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String detect() {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			Map<String, Object> map = new HashMap<>();
			String image = Base64Util.encode(FileUtil.readFileByBytes("E:\\photo_test\\face.png"));
			map.put("image", image);
			map.put("image_type", "BASE64");
			//最多处理人脸的数目，默认值为1，仅检测图片中面积最大的那个人脸；最大值10，检测图片中面积最大的几张人脸。
			map.put("max_face_num", "10");
			/*LIVE表示生活照：通常为手机、相机拍摄的人像图片、或从网络获取的人像图片等
			IDCARD表示身份证芯片照：二代身份证内置芯片中的人像照片
			WATERMARK表示带水印证件照：一般为带水印的小图，如公安网小图
			CERT表示证件照片：如拍摄的身份证、工卡、护照、学生证等证件图片
			默认LIVE*/
			map.put("face_type", "LIVE");
			String param = GsonUtils.toJson(map);
			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = "24.b8cd3dae8ac564c7a3d4b4db79012818.2592000.1542784650.282335-14439847";
			String result = HttpUtil.post(url, accessToken, "application/json", param);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		detect();
	}
	
	/*返回结果
	字段	必选	类型	说明
	face_num	是	int	检测到的图片中的人脸数量
	face_list	是	array	人脸信息列表，具体包含的参数参考下面的列表。
	+face_token	是	string	人脸图片的唯一标识
	+location	是	array	人脸在图片中的位置
	++left	是	double	人脸区域离左边界的距离
	++top	是	double	人脸区域离上边界的距离
	++width	是	double	人脸区域的宽度
	++height	是	double	人脸区域的高度
	++rotation	是	int64	人脸框相对于竖直方向的顺时针旋转角，[-180,180]
	+face_probability	是	double	人脸置信度，范围【0~1】，代表这是一张人脸的概率，0最小、1最大。
	+angel	是	array	人脸旋转角度参数
	++yaw	是	double	三维旋转之左右旋转角[-90(左), 90(右)]
	++pitch	是	double	三维旋转之俯仰角度[-90(上), 90(下)]
	++roll	是	double	平面内旋转角[-180(逆时针), 180(顺时针)]
	+age	否	double	年龄 ，当face_field包含age时返回
	+beauty	否	int64	美丑打分，范围0-100，越大表示越美。当face_fields包含beauty时返回
	+expression	否	array	表情，当 face_field包含expression时返回
	++type	否	string	none:不笑；smile:微笑；laugh:大笑
	++probability	否	double	表情置信度，范围【0~1】，0最小、1最大。
	+face_shape	否	array	脸型，当face_field包含face_shape时返回
	++type	否	double	square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
	++probability	否	double	置信度，范围【0~1】，代表这是人脸形状判断正确的概率，0最小、1最大。
	+gender	否	array	性别，face_field包含gender时返回
	++type	否	string	male:男性 female:女性
	++probability	否	double	性别置信度，范围【0~1】，0代表概率最小、1代表最大。
	+glasses	否	array	是否带眼镜，face_field包含glasses时返回
	++type	否	string	none:无眼镜，common:普通眼镜，sun:墨镜
	++probability	否	double	眼镜置信度，范围【0~1】，0代表概率最小、1代表最大。
	+race	否	array	人种 face_field包含race时返回
	++type	否	string	yellow: 黄种人 white: 白种人 black:黑种人 arabs: 阿拉伯人
	++probability	否	double	人种置信度，范围【0~1】，0代表概率最小、1代表最大。
	+face_type	否	array	真实人脸/卡通人脸 face_field包含face_type时返回
	++type	否	string	human: 真实人脸 cartoon: 卡通人脸
	++probability	否	double	人脸类型判断正确的置信度，范围【0~1】，0代表概率最小、1代表最大。
	+landmark	否	array	4个关键点位置，左眼中心、右眼中心、鼻尖、嘴中心。face_field包含landmark时返回
	+landmark72	否	array	72个特征点位置 face_field包含landmark时返回
	+quality	否	array	人脸质量信息。face_field包含quality时返回
	++occlusion	否	array	人脸各部分遮挡的概率，范围[0~1]，0表示完整，1表示不完整
	+++left_eye	否	double	左眼遮挡比例，[0-1] ，1表示完全遮挡
	+++right_eye	否	double	右眼遮挡比例，[0-1] ， 1表示完全遮挡
	+++nose	否	double	鼻子遮挡比例，[0-1] ， 1表示完全遮挡
	+++mouth	否	double	嘴巴遮挡比例，[0-1] ， 1表示完全遮挡
	+++left_cheek	否	double	左脸颊遮挡比例，[0-1] ， 1表示完全遮挡
	+++right_cheek	否	double	右脸颊遮挡比例，[0-1] ， 1表示完全遮挡
	+++chin	否	double	下巴遮挡比例，，[0-1] ， 1表示完全遮挡
	++blur	否	double	人脸模糊程度，范围[0~1]，0表示清晰，1表示模糊
	++illumination	否	double	取值范围在[0~255], 表示脸部区域的光照程度 越大表示光照越好
	++completeness	否	int64	人脸完整度，0或1, 0为人脸溢出图像边界，1为人脸都在图像边界内*/
}