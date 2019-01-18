package com.godngu.webservice.service;

import com.godngu.webservice.domain.posts.PostsRepository;
import com.godngu.webservice.dto.PostsMainResponseDto;
import com.godngu.webservice.dto.PostsSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PostsService {

    private PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    @Transactional(readOnly = true)
    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                              .map(PostsMainResponseDto::new)
                              .collect(Collectors.toList());
    }
}
