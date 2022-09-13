package com.trebnikau.service;

import com.trebnikau.entity.Refund;
import com.trebnikau.repo.RefundRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RefundService {

    @Autowired
    private RefundRepo refundRepo;

    public String showAllRefunds(Model model) {
        Iterable<Refund> refunds = refundRepo.findAll();
        model.addAttribute("refunds", refunds);
        return "show-all-refunds";
    }
}
