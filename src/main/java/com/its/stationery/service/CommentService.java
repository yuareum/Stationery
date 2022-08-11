package com.its.stationery.service;

import com.its.stationery.dto.CommentDTO;
import com.its.stationery.entity.CommentEntity;
import com.its.stationery.entity.InquiryEntity;
import com.its.stationery.repository.CommentRepository;
import com.its.stationery.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final InquiryRepository inquiryRepository;
    private final CommentRepository commentRepository;

    public Long save(CommentDTO commentDTO) {
        Optional<InquiryEntity> optionalInquiryEntity= inquiryRepository.findById(commentDTO.getCommentInquiryId());
        if(optionalInquiryEntity.isPresent()){
            Long saveId = commentRepository.save(CommentEntity.toSaveEntity(commentDTO, optionalInquiryEntity.get())).getId();
            return saveId;
        }
        return null;
    }

    public List<CommentDTO> findAll(Long commentInquiryId) {
        List<CommentEntity> commentEntityList = commentRepository.findByCommentInquiryId(commentInquiryId);
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(CommentEntity comment: commentEntityList){
            commentDTOList.add(CommentDTO.toCommentDTO(comment));
        }
        return commentDTOList;
    }

    public CommentDTO findById(Long id) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findById(id);
        if(optionalCommentEntity.isPresent()){
            CommentDTO commentDTO = CommentDTO.toCommentDTO(optionalCommentEntity.get());
            return commentDTO;
        }
        return null;
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
