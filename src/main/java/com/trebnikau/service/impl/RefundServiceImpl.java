package com.trebnikau.service.impl;

import com.trebnikau.entity.Refund;
import com.trebnikau.repo.RefundRepo;
import com.trebnikau.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RefundServiceImpl implements RefundService {

    @Autowired
    private RefundRepo refundRepo;

    @Override
    public String showAllRefunds(Model model) {
        Iterable<Refund> refunds = refundRepo.findAll();
        model.addAttribute("refunds", refunds);
        return "show-all-refunds";
    }
}
