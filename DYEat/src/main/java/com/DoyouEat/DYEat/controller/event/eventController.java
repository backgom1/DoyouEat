package com.DoyouEat.DYEat.controller.event;

import com.DoyouEat.DYEat.controller.menu.ImageForm;
import com.DoyouEat.DYEat.controller.menu.MenuForm;
import com.DoyouEat.DYEat.controller.order.OrderForm;
import com.DoyouEat.DYEat.domain.DYE_Event;
import com.DoyouEat.DYEat.domain.DYE_Menu;
import com.DoyouEat.DYEat.repository.event.EventApiRepository;
import com.DoyouEat.DYEat.repository.menu.menuFile.MenuFile;
import com.DoyouEat.DYEat.service.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/event")
public class eventController {
    private final EventService eventService;
    private final MenuFile menuFile;

    private final EventApiRepository eventApiRepository;

    @Value("${file.dir}")
    private String fileDir;
    @GetMapping
    public String event(Model model, Long id){
        List<DYE_Event> events = eventService.findAll(id);
        model.addAttribute("event",events);
        return "/views/event/event";
    }


    @GetMapping("/admin/new")
    public String newEvent(@ModelAttribute EventForm form){
        return "/views/event/eventNewList";
    }

    @PostMapping("/admin/new")
    public String eventNew(@ModelAttribute EventForm form,@RequestParam MultipartFile mainFile) throws IOException {
        DYE_Event dye_event = new DYE_Event();
        dye_event.setTitle(form.getTitle());
        dye_event.setText(form.getText());

        //1이면 진행 0이면 종료 기본은 1로 설정
        dye_event.setOnoff(form.getOnoff());

        String originalFilename = mainFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        dye_event.setPicture_ori(originalFilename);
        dye_event.setPicture_save(storeFileName);

        String fullPath = fileDir + storeFileName;
        mainFile.transferTo(new File(fullPath));


        eventService.saveMenus(dye_event);
        return "redirect:/event";
    }

    @GetMapping("/{id}/detail")
    public String items(@ModelAttribute EventForm form, @PathVariable Long id, Model model) throws IOException {
        DYE_Event event = eventService.findById(id);
        form.setTitle(event.getTitle());
        form.setText(event.getText());
        model.addAttribute("eventId", event);
        return "/views/event/eventDetail";
    }


    //삭제
    @GetMapping("/admin/{id}/delete")
    public String deleteItems(@PathVariable Long id,@ModelAttribute EventForm form) {
        eventApiRepository.deleteById(id);
        return "redirect:/event";
    }

    @GetMapping("/admin/{id}/update")
    public String updateEvent(@PathVariable Long id,@ModelAttribute EventForm form,Model model) {
        DYE_Event dye_event = eventService.findById(id);
        form.setOnoff(dye_event.getOnoff());
        model.addAttribute("updateEvent",form);
        return "/views/event/eventEditList";
    }

    @PostMapping("/admin/{id}/update")
    public String saveUpdateEvent(@PathVariable Long id,@ModelAttribute EventForm form) {
        DYE_Event dye_event = eventService.findById(id);
        dye_event.setOnoff(form.getOnoff());
        eventApiRepository.save(dye_event);
        return "redirect:/event";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //해당 경로에 있는 파일 경로를 잡는것
        return new UrlResource("file:"+menuFile.getFullPath(filename));
    }

    private String createStoreFileName(String originalFilename) {
        //image.png면
        String s = UUID.randomUUID().toString();
        String ext = extractExt(originalFilename);
        //서버에 저장하는 파일명


        return s + "." + ext;
    }

    //확장자를 띄어주는 작업
    private String extractExt(String originalFilename) {
        int ps = originalFilename.lastIndexOf(".");
        return originalFilename.substring(ps + 1);

    }
}
