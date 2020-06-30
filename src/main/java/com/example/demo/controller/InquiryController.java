package com.example.demo.controller;

import java.util.List;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.InquiryForm;
import com.example.demo.entity.Inquiry;
import com.example.demo.service.InquiryService;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	
	private InquiryService service;
	
	@Autowired
	public InquiryController(InquiryService service) {
		this.service=service;
	}
	

	//新規入力ページ
	@GetMapping("/form")
	public String form(InquiryForm inquiryForm,Model model) {
		model.addAttribute("headLine","入力してください");
		return "form";
	}
	
	
	//確認画面で戻るボタンを押したら
	@PostMapping("/form")
	public String againeForm(InquiryForm inquiryForm, Model model){
		model.addAttribute("inquiryForm",inquiryForm);
		model.addAttribute("headLine","入力してください");
		return "form";
	}
	
	//新規入力ページで確認ボタンを押して入力チェック。
	@PostMapping("/confirm")
	public String sendForm(@Validated InquiryForm inquiryForm,BindingResult result,Model model) {
		//入力チェックが通らなかったら。
		if(result.hasErrors()) {
			model.addAttribute("inquiryForm",inquiryForm);
			model.addAttribute("headLine","正しく入力してください");
			return "form";
		}else {
			//入力チェックが通ったら。
		model.addAttribute("inquiryForm",inquiryForm);
		model.addAttribute("headLine","内容を確認してください");
		return "conf";
	}
	}
	
	
	//確認画面で送信ボタンを押したらDBに保存、その後一覧画面に遷移
	@PostMapping("/save")
	public String save(Inquiry inquiry,InquiryForm inquiryForm, Model model) {
		inquiry.setName(inquiryForm.getName());
		inquiry.setPass(inquiryForm.getPass());
		inquiry.setEmail(inquiryForm.getEmail());
		inquiry.setText(inquiryForm.getText());
		service.save(inquiry);
//		return "redirect:/inquiry/list";       //マッピングのパスを指定する
		return "conplete"; 
	}
	
	//入力画面で一覧画面リンクを押下したら、一覧画面に遷移
	@GetMapping("/list")
	public String list(Model model) {
		List<Inquiry> list = service.findAll();
		model.addAttribute("inquiryForm",list);
		model.addAttribute("headLine","お問い合わせ一覧");
//		Inquiry list = new Inquiry();
		model.addAttribute("inquiryForm",list);
//		model.addAttribute("headLine","お問い合わせ完了");
//		return "list";
		return "list";
	}
	
	@GetMapping("/result")
	public String result(@RequestParam("name") String name,@RequestParam("pass") String pass, Model model) {
//		public String result(@PathVariable("name") String name,@PathVariable("password") String password, Model model) {
		List<Inquiry> list = service.findByNameAndPass(name,pass);
//		List<Inquiry> list = service.findByName(name);
		model.addAttribute("inquiryForm",list);
		model.addAttribute("headLine","お問い合わせ一覧");
//		return "redirect:/inquiry/list";
		return "result";
	}
	
	@GetMapping("/search")
	public String search(Model model) {
		model.addAttribute("headLine","お問い合わせ検索");
		return "search";
		
	}
	
	
	
	
	//一覧画面でidを押下して詳細画面に遷移
	@GetMapping("/{id}")
	public String show(@PathVariable Long id,Model model) {
		Inquiry inquiry = service.findById(id);
		model.addAttribute("inquiryForm",inquiry);
		model.addAttribute("headLine","詳細画面");
		return "show";
	}
	
	
	//詳細画面で詳細ボタンを押下して編集画面に遷移
	@GetMapping("/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		Inquiry inquiry = service.findById(id);
		model.addAttribute("inquiryForm",inquiry);
		model.addAttribute("headLine","編集画面");
		return "edit";
	}
	
	
	//編集画面で入力に問題が無ければ変更を保存
	@PutMapping("{id}")    //PutMappingのときはリクエストが"{id}"になる
	public String update(@Validated InquiryForm inquiryForm,BindingResult result,@PathVariable Long id,@ModelAttribute Inquiry inquiry,Model model) {//dtoにバリデーションを記述しているので@ValidatedをInquiryFormに着ける
		//入力にエラーがある場合、編集画面に遷移
		if(result.hasErrors()) {
//			Inquiry inquiryForm = service.findById(id);
			model.addAttribute("inquiryForm",inquiryForm);
			model.addAttribute("headLine","正しく入力してください");
			return "edit";
		}else {
		inquiry.setId(id);
		service.save(inquiry);
		model.addAttribute("inquiryForm",inquiry);
		model.addAttribute("headLine","詳細画面");
		return "show";
		}
	}
	
	//詳細画面で削除ボタンを押下
	@DeleteMapping("{id}")
	public String remove(@PathVariable Long id,Model model) {
		service.deleteById(id);
		model.addAttribute("headLine","一覧画面");
		return "redirect:/inquiry/list";
	}
	

}

//エンドポイント
	//get:form      新規入力ページに遷移
	//post:form     確認画面で戻るボタンを押したら
	//post:confirm　  確認ボタンを押して入力チェックが通らなかったら、新規入力ページに遷移。入力チェックが通ったら、確認画面に遷移
	//post:save     確認画面で送信ボタンを押したら
	//get:list      入力画面で一覧画面リンクを押下したら
	//get:{id}      一覧画面で詳細ボタンを押したら
	//get:{id}/edit 詳細画面で編集ボタンを押したら
	//put:{id}    編集画面で変更ボタンを押したら
	//delete:{id}   詳細画面で削除ボタンを押したら
