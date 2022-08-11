package vn.techmaster.comic.service;

import vn.techmaster.comic.entity.Story;

import java.util.List;
import java.util.Optional;

public interface StoryService {
  List<Story> findAll();

  Optional<Story> findById(Long id);

  Story saveStory(Story story);

  void updateStory(long id, Story story);

  void deleteById(long id);

}
