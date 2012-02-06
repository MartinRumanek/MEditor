import cz.fi.muni.xkremser.editor.shared.domain.DigitalObjectModel;

    /** The model. */
    private DigitalObjectModel model;
     *        the file model
     * @param model
     *        the model
    public StoredItem(String file_name,
                      String uuid,
                      DigitalObjectModel model,
                      String description,
                      String storedDate) {
        this.model = model;
     * Instantiates a new digital object basic inform.
     * 
     * @param fileName
     */

    public StoredItem(String fileName) {
        super();
        this.fileName = fileName;
    }

    /**
     * Gets the file model.
     * @return the file model
     * Sets the file model.
     *        the new file model
     * Gets the model.
     * @return the model
    public DigitalObjectModel getModel() {
        return model;
     * Sets the model.
     * @param model
     *        the new model
    public void setModel(DigitalObjectModel model) {
        this.model = model;
        return "StoredItem [uuid=" + uuid + ", model=" + model + ", description=" + description