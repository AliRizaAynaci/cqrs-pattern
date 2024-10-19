package com.example.product.core.application.commands;

public class DeleteProductCommand {

        private Long id;

        public DeleteProductCommand(Long id) {
            this.id = id;
        }

        public Long getId() {
            return id;
        }
}
