package com.thoughtworks.todo.service;

import com.thoughtworks.todo.model.TodoItem;
import com.thoughtworks.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public Optional<List<TodoItem>> getAllTodo() {
        List<TodoItem> allItems = todoRepository.findAll();
        return Optional.of(allItems);
    }

    public List<TodoItem> save(List<TodoItem> todoItem) throws Exception {
        List<TodoItem> validatedItems = validate(todoItem);
        try {
            todoRepository.saveAll(validatedItems);
        } catch (Exception e) {
            throw new Exception("Something went wrong while saving {}", e);
        }
        return validatedItems;

    }

    private List<TodoItem> validate(List<TodoItem> listOfItems) {
        return listOfItems
                .stream()
                .filter(item -> Objects.nonNull(item.getTitle()))
                .collect(Collectors.toList());
    }

    public TodoItem deleteById(Long id) {
        Optional<TodoItem> findById = todoRepository.findById(id);
        findById.ifPresent(item -> todoRepository.deleteById(item.getId()));
        return findById.orElse(null);

    }

    public List<TodoItem> deleteAllTodo() {
        Optional<List<TodoItem>> deletedItems = Optional.of(todoRepository.findAll());
        deletedItems.ifPresentOrElse(this::deleteAllItems, null);
        return deletedItems.get();
    }

    private void deleteAllItems(List<TodoItem> todoItems) {
        todoRepository.deleteAll(todoItems);
    }


    public TodoItem updateById(Long id) {
        Optional<TodoItem> item = todoRepository.findById(id);
        item.ifPresent(todoItem -> {
            todoItem.setIsDone(Boolean.FALSE);
            todoRepository.save(todoItem);

        });
        return item.get();
    }
}
