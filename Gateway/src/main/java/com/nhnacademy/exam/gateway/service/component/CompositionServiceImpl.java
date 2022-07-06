package com.nhnacademy.exam.gateway.service.component;

import com.nhnacademy.exam.gateway.adapter.composition.CompositionAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompositionServiceImpl
    implements CompositionService {
    private final CompositionAdapter compositionAdapter;
}
