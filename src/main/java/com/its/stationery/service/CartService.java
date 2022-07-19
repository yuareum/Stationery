package com.its.stationery.service;

import com.its.stationery.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository shoppingCartRepository;
}
