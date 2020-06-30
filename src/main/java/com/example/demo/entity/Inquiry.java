package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

//import java.util.Date;

import lombok.Data;

@Data
@Entity                            //エンティティクラスであることを示す
@Table(name="info")                //テーブル名を明示的に指定（指定しない場合はクラス名がテーブル名になる）
public class Inquiry {
	
	@Id                            //主キーに指定。属性またはgetterメソッドにつける。
	@GeneratedValue(strategy=GenerationType.IDENTITY)                   //主キーの値が自動生成
	private Long id;     //privateにしないといけない？
	@NotEmpty                 //エンティティクラスにバリデーションの設定をする
	private String name;
	
	@NotEmpty
	private String pass;
	
	@NotEmpty
	private String email;
	@NotEmpty
	@Size(max = 20)
	private String text;
	
}