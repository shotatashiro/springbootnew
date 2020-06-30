package com.example.demo.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


import lombok.Data;

@Data
public class InquiryForm {
	
	
	@Id                            //主キーに指定。属性またはgetterメソッドにつける。
	@GeneratedValue(strategy=GenerationType.IDENTITY)                   //主キーの値が自動生成
	private Long id;     //privateにしないといけない？
	@NotEmpty(message="入力は必須です")              //エンティティクラスにバリデーションの設定をする
	private String name;
	
	@NotEmpty(message="パスワードは必須です") 
	private String pass;
	
	@NotEmpty(message="入力は必須です")   
	@Email(message="正しい形式で入力してください")   
	private String email;
	@NotEmpty(message="入力は必須です")   
	@Size(max = 100,message="100文字以内で入力してください")
	private String text;

}
